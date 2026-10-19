package com.practice.interview;

import java.util.HashMap;
import java.util.Map;

public class LFUCache {

    int size;
    Map<Integer,Node>cache ;
    Map<Integer,DLL> lfuState;
    int minFreq=1;

    static class Node{
        int freq=1;
        Node next=null;
        Node prev=null;
        Integer key;
        Integer val;
         Node(Integer key, Integer val){
             this.key=key;
             this.val=val;

         }
    }
    static class DLL{
        int size=0;
        Node head;
        Node tail;

        DLL(){
            head= new Node(null,null);
            tail = new Node(null,null);
            head.next=tail;
            tail.prev=head;
        }

        void remove(Node node){
            node.prev.next= node.next;
            node.next.prev = node.prev;
            node.next=null;
            node.prev=null;
            size--;

        }
        Node giveTail(){
            return tail.prev;
        }
        void addToTheFront(Node node){
            node.next= head.next;
            node.prev=head;
            head.next.prev=node;
            head.next=node;
            size++;
        }

        public void removeTail() {
       remove(tail.prev);
        }
    }

    LFUCache(int size){
        this.size=size;
        cache = new HashMap<>();
       lfuState = new HashMap<>();
    }

    int get(int key){
        Node node = cache.get(key);
        if(node ==null)
            return -1;
        incrFreqOfNode(node);
        return node.val;
    }

    void put(int key,int val){
        Node node = cache.get(key);

        if(node ==null) {
            if(cache.size()==size){
                Node lfuunode  = deleteLfuNode(minFreq);
                cache.remove(lfuunode.key);
            }
            node = new Node(key,val);
            cache.put(key,node);
            lfuState.computeIfAbsent(1,dll-> new DLL()).addToTheFront(node);
            minFreq=1;
        }else{
            node.val= val;
            incrFreqOfNode(node);
        }


    }

    private void incrFreqOfNode(Node node) {

        int oldFreq= node.freq;
        int newFreq= oldFreq+1;
        node.freq=newFreq;
        DLL oldDll= lfuState.get(oldFreq);
        Node tail = oldDll.giveTail();
       oldDll.remove(tail);
        if(oldDll.size==0)
            lfuState.remove(oldFreq);

        lfuState.computeIfAbsent(newFreq,dll-> new DLL()).addToTheFront(node);
        if( oldDll.size==0 && minFreq==oldFreq)
            minFreq= newFreq;

    }

    private Node deleteLfuNode(int freq) {
        DLL oldDll= lfuState.get(freq);
        Node tail = oldDll.giveTail();
        oldDll.removeTail();
        if(oldDll.size==0)
            lfuState.remove(freq);
        return tail;
    }

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2);
//        cache.put(1, 10);
//        cache.put(2, 20);
//        System.out.println(cache.get(1));
//        cache.put(3, 30);
//        System.out.println(cache.get(2)); // expected -1
//        System.out.println(cache.get(1));// expected 10
//        System.out.println(cache.get(3)); // expected 30

        cache.put(1, 10);
        cache.put(2, 20);
        cache.get(1);       // key 1: frequency 2
        cache.get(2);       // key 2: frequency 2
        cache.put(3, 30);   // evict key 1: both freq 2, key 1 is LRU
        System.out.println(cache.get(1)); // -1
        System.out.println(cache.get(2)); // 20
        System.out.println(cache.get(3)); // 30
    }
}
