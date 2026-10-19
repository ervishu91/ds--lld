package com.practice.ds.LinkedList;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKlist {

    static class Node{
        int val;
        Node next=null;
        Node(int val){
            this.val=val;
        }

    }
    public static void main(String[] args) {
        Node l1 = new Node(1);
        l1.next = new Node(4);
        l1.next.next = new Node(7);

        Node l2 = new Node(2);
        l2.next = new Node(5);
        l2.next.next = new Node(8);

        Node l3 = new Node(3);
        l3.next = new Node(6);
        l3.next.next = new Node(9);

        System.out.println("merged list is ::"+ mergeKlist(new Node[]{l1,l2,l3}));
    }

    private static String mergeKlist(Node[] nodes) {
        Node head=null,temp=null;
        PriorityQueue<Node> heap = new PriorityQueue<>(Comparator.comparing(a->a.val));
        for(Node node : nodes)
            heap.offer(node);
        while(!heap.isEmpty()){
            Node curr = heap.poll();
            if(head==null){
                head=curr;
                temp=head;
            }else{
                temp.next=curr;
                temp=curr;
            }
            if(curr.next!=null)
                heap.offer(curr.next);
        }
        StringBuffer sb = new StringBuffer();;

        while(head!=null){
            sb.append(head.val);
            head=head.next;
            sb.append(",");
        }
        return sb.toString();
    }
}
