
package com.practice.interview;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class LRU<K, V> implements AutoCloseable {

    private final int capacity;
    private final long ttlNanos;

    private final Map<K, Node<K, V>> cache;
    private final DelayQueue<ExpiryTask<K>> expiryQueue;

    private final Node<K, V> head;
    private final Node<K, V> tail;

    private long versionSequence = 0;
    private volatile boolean running = true;
    private final Thread cleanupThread;

    // Cache entry and doubly linked list node
    private static class Node<K, V> {
        final K key;
        V value;

        Node<K, V> prev;
        Node<K, V> next;

        long version;
        long expiryTimeNanos;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    // Immutable expiry record stored in DelayQueue
    private static final class ExpiryTask<K> implements Delayed {
        private final K key;
        private final long version;
        private final long expiryTimeNanos;

        ExpiryTask(K key, long version, long expiryTimeNanos) {
            this.key = key;
            this.version = version;
            this.expiryTimeNanos = expiryTimeNanos;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            long remaining = expiryTimeNanos - System.nanoTime();
            return unit.convert(remaining, TimeUnit.NANOSECONDS);
        }

        @Override
        public int compareTo(Delayed other) {
            ExpiryTask<?> task = (ExpiryTask<?>) other;

            long now = System.nanoTime();

            return Long.compare(
                    expiryTimeNanos - now,
                    task.expiryTimeNanos - now
            );
        }
    }

    public LRU(int capacity, long ttlMillis) {
        if (capacity <= 0) {
            throw new IllegalArgumentException(
                    "Capacity must be positive"
            );
        }

        if (ttlMillis <= 0) {
            throw new IllegalArgumentException(
                    "TTL must be positive"
            );
        }

        this.capacity = capacity;
        this.ttlNanos =
                TimeUnit.MILLISECONDS.toNanos(ttlMillis);

        this.cache = new HashMap<>();
        this.expiryQueue = new DelayQueue<>();

        head = new Node<>(null, null);
        tail = new Node<>(null, null);

        head.next = tail;
        tail.prev = head;

        cleanupThread = new Thread(
                this::removeExpiredEntries,
                "LRU-TTL-Cleanup"
        );

        cleanupThread.setDaemon(true);
        cleanupThread.start();
    }

    // Returns null for a missing or expired entry.
    public synchronized V get(K key) {
        Node<K, V> node = cache.get(key);

        if (node == null) {
            return null;
        }

        // Expiration must be checked even if the worker
        // has not yet processed the corresponding task.
        if (System.nanoTime() >= node.expiryTimeNanos) {
            cache.remove(key);
            deleteNode(node);
            return null;
        }

        // Refresh sliding TTL and invalidate old tasks.
        refreshExpiry(node);

        // Mark this entry as most recently used.
        deleteNode(node);
        addNodeToFront(node);

        return node.value;
    }

    public synchronized void put(K key, V value) {
        Node<K, V> node = cache.get(key);

        if (node != null) {
            // Update existing entry.
            node.value = value;

            deleteNode(node);
            addNodeToFront(node);

            refreshExpiry(node);
            return;
        }

        // Evict LRU before inserting a new entry.
        if (cache.size() >= capacity) {
            Node<K, V> lru = tail.prev;

            deleteNode(lru);
            cache.remove(lru.key);
        }

        node = new Node<>(key, value);

        cache.put(key, node);
        addNodeToFront(node);

        refreshExpiry(node);
    }

    private void refreshExpiry(Node<K, V> node) {
        // Globally unique generation for every refresh.
        long version = ++versionSequence;

        long expiryTime = System.nanoTime() + ttlNanos;

        node.version = version;
        node.expiryTimeNanos = expiryTime;

        // Never mutate this task after enqueueing it.
        expiryQueue.offer(
                new ExpiryTask<>(
                        node.key,
                        version,
                        expiryTime
                )
        );
    }

    private void addNodeToFront(Node<K, V> node) {
        node.next = head.next;
        node.prev = head;

        head.next.prev = node;
        head.next = node;
    }

    private void deleteNode(Node<K, V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;

        node.prev = null;
        node.next = null;
    }

    private void removeExpiredEntries() {
        while (running) {
            try {
                // Blocks until the earliest task expires.
                ExpiryTask<K> task = expiryQueue.take();

                synchronized (this) {
                    Node<K, V> node = cache.get(task.key);

                    if (node == null) {
                        continue;
                    }

                    // Ignore stale expiry tasks.
                    if (node.version != task.version) {
                        continue;
                    }

                    // Defensively verify the current deadline.
                    if (System.nanoTime() < node.expiryTimeNanos) {
                        continue;
                    }

                    cache.remove(task.key);
                    deleteNode(node);
                }

            } catch (InterruptedException e) {
                if (!running) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }

    public synchronized int size() {
        return cache.size();
    }

    @Override
    public void close() {
        running = false;
        cleanupThread.interrupt();
    }

    public static void main(String[] args)
            throws InterruptedException {

        try (LRU<String, Integer> lru = new LRU<>(2, 2000)) {
            lru.put("A", 10);
            lru.put("B", 20);

            System.out.println(lru.get("A")); // 10; refreshes TTL

            lru.put("C", 30); // Evicts B (LRU)

            System.out.println(lru.get("B")); // null
            System.out.println(lru.get("A")); // 10

            Thread.sleep(2500);

            // A may have been refreshed by the preceding get.
            // C has not been accessed since insertion.
            System.out.println(lru.get("C")); // null
        }
    }
}
