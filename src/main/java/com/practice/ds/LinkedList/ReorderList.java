package com.practice.ds.LinkedList;

public class ReorderList {

    static class Node {
        int key;
        Node next;

        Node(int key) {
            this.key = key;
        }
    }

    public static void main(String[] args) {

        Node root = new Node(1);
        root.next = new Node(2);
        root.next.next = new Node(3);
        root.next.next.next = new Node(4);
        root.next.next.next.next = new Node(5);

        reorderLL(root);

        printList(root);
    }

    private static void reorderLL(Node root) {
        Node slow=root,fast=root.next;
        while(fast!=null && fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
        }
        System.out.println("slow::"+slow.key);
        Node second=slow.next;
        Node first = root;
        slow.next=null;
        System.out.println("first::"+first.key+"second::"+second.key);
        Node prev=null,current=second,next;
        while(current!=null){
            next=current.next;
            current.next=prev;
            prev=current;
            current=next;
        }
        second=prev;
        while(second!=null){
            Node fnext=first.next;
            Node snext= second.next;
            first.next=second;
            second.next=fnext;
            first=fnext;
            second=snext;
        }

    }

    private static void printList(Node root) {

        Node temp = root;

        while (temp != null) {
            System.out.print(temp.key);

            if (temp.next != null) {
                System.out.print(" -> ");
            }

            temp = temp.next;
        }

        System.out.println();
    }
}