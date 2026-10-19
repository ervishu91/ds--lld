package com.practice.lld.LRU;

import java.util.HashMap;
import java.util.Map;

public class LRUCache<K,V>{

    int capacity;
    Node<K,V> head;
    Node<K,V> tail;
    Map<K,Node<K,V>> cache;


    static class Node<K,V>{
        K key;
        V val;
        Node<K,V> prev=null;
        Node<K,V> next=null;

        public Node(K key, V val){
            this.key=key;
            this.val= val;
        }
    }

    public LRUCache(int capacity){
        this.capacity = capacity;
        cache = new HashMap<>();
        head = new Node<>(null,null);
        tail = new Node<>(null,null);
        head.next=tail;
        tail.prev=head;
    }

    public V get(K key){
        Node<K,V> node = cache.get(key);
        if(node ==null){
            return null;
        }
        moveNodeToTheHead(node);
        return node.val;



    }
    public void put(K  key, V value){
        Node<K,V> node = cache.get(key);
        if(node !=null){
            node.val=value;
            moveNodeToTheHead(node);
        }else{
            if(cache.size()==capacity){
                Node<K,V> lruNode = deleteLruNode();
                cache.remove(lruNode.key);
            }
            node = new Node<>(key,value);
            addNodeToTheHead(node);
            cache.put(key,node);
        }

    }

    private Node<K,V> deleteLruNode() {
        Node<K,V> node = tail.prev;
        deleteNode(node);
        return node;
    }

    private void deleteNode(Node<K,V> node) {
        node.next.prev= node.prev;
        node.prev.next= node.next;
        node.next=null;
        node.prev=null;
    }

    private void addNodeToTheHead(Node<K,V> node) {
        node.next= head.next;
        node.prev=head;
        node.next.prev=node;
        head.next=node;
    }

    private void moveNodeToTheHead(Node<K,V> node) {
        deleteNode(node);
        addNodeToTheHead(node);

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
