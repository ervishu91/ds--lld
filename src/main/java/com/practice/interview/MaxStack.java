package com.practice.interview;

import java.util.*;

public class MaxStack {

    static  class Node{
        Node prev=null;
        Node next=null;
        int val;
        Node(int val){
            this.val=val;
        }
    }

     int size=0;
    Node head ;
    Node tail ;
    TreeMap<Integer, List<Node>> nodeMap ;


    MaxStack(){
         nodeMap = new TreeMap<>();
          head = new Node(-1);
          tail = new Node(-1);
         head.next=tail;
         tail.prev=head;
    }

    void push(int x){
        Node node = new Node(x);
        node.next=head.next;
        node.prev = head;
        head.next.prev=node;
        head.next=node;

        nodeMap.computeIfAbsent(x,list-> new LinkedList<>()).addFirst(node);
        size++;
    }
    int pop(){
        if(size==0)
            return -1;
        Node node = head.next;
        head.next =node.next;
        node.next.prev= node.prev;
        node.next=null;
        node.prev=null;
        nodeMap.get(node.val).removeFirst();
        if(nodeMap.get(node.val).isEmpty()){
         nodeMap.remove(node.val);
        }


        size--;
        return node.val;
    }
    int peek(){
        if(size==0)
            return -1;
        Node node = head.next;
        return node.val;

    }
    int max(){
        if(size==0)
            return -1;
        return nodeMap.lastKey();
    }
    void popmax(){
        if(size==0) {
            System.out.println("empty stack");
            return;
        }
         Node node = nodeMap.lastEntry().getValue().removeFirst();
        node.prev.next= node.next;
        node.next.prev= node.prev;
        node.next=null;
        node.prev=null;
        if(nodeMap.get(node.val).isEmpty()){
            nodeMap.remove(node.val);
        }
        size--;
    }
    public static void main(String[] args) {
        MaxStack stack = new MaxStack();
        stack.push(5);
        stack.push(1);
        stack.push(5);
        stack.pop();
        stack.popmax();
        System.out.println("max::"+stack.max());


    }

}
