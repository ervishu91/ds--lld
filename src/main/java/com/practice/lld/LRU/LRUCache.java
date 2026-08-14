package com.practice.lld.LRU;

import java.util.HashMap;
import java.util.Map;

public class LRUCache<K, V> {

    int capacity;
    Map<K,Node<K,V>> cache;
    Node<K,V> head;
    Node<K,V> tail;

    static class Node <K,V>{
        K k;
        V value;
        Node<K,V> next=null;
        Node<K,V> prev=null;

        Node(K k,V v){
            this.k=k;
            this.value=v;
        }
    }

    public LRUCache(int capacity){
        if (capacity < 1) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        this.capacity = capacity;
        this.head = new Node<>(null,null);
        this.tail = new Node<>(null,null);
        this.cache = new HashMap<>();
        this.head.next= this.tail;
        this.tail.prev = this.head;
    }

    public V get(K key){
        Node<K,V> node = cache.get(key);
        if(node==null){
            return null;
        }
        deleteNode(node);
        addToTheHead(node);

        return node.value;
    }
    public void put(K key, V value){
        Node<K,V> node = cache.get(key);
        if(node!=null){
            node.value = value;
            deleteNode(node);
            addToTheHead(node);
        }
        else{
            Node<K,V> newNode = new Node<>(key, value);
            cache.put(key,newNode);
            addToTheHead(newNode);
            if(cache.size()>capacity){
                Node<K,V> LRUNode = deleteLRUNode();
                cache.remove(LRUNode.k);
            }
        }
    }

    private void deleteNode(Node<K, V> node){
        node.prev.next = node.next;
        node.next.prev= node.prev;
        node.next=null;
        node.prev=null;

    }
    private void addToTheHead(Node<K, V> node){
        node.next= head.next;
        node.prev= head;
        head.next.prev= node;
        head.next = node;
    }
    private Node<K, V> deleteLRUNode(){
        Node<K, V> tailnode = tail.prev;
        deleteNode(tailnode);
        return tailnode;
    }

    public static void main(String[] args) {
        LRUCache<Integer, String> cache = new LRUCache<>(2);
        cache.put(1, "one");
        cache.put(2, "two");

        System.out.println("get(1): " + cache.get(1)); // one; key 1 is now most recently used

        cache.put(3, "three"); // evicts key 2
        System.out.println("get(2): " + cache.get(2)); // null
        System.out.println("get(3): " + cache.get(3)); // three
    }

}
