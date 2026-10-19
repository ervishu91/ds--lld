package com.practice.ds.LinkedList;

import javax.crypto.spec.PSource;

public class RemoventhNode {
    static class Node {
        int key;
      Node next;

        Node(int key) {
            this.key = key;
        }
    }

    public static void main(String[] args) {

        Node root  =new Node(1);
        root.next = new Node(2);
        root.next.next = new Node(3);
        root.next.next.next = new Node(4);
        root.next.next.next.next = new Node(5);
        removenthNode(root,4);
        printList(root);
    }
    //1-2-3-4-5
    //1-2-4-5
    private static void removenthNode(Node root, int k) {

        Node head = new Node(-1);
        head.next=root;
        Node temp = root;
        for(int i=1;i<=k;i++) {
            if(temp==null)
                return;
            temp=temp.next;
        }

        System.out.println(temp.key);
        Node slow =root;
        while(temp!=null) {
            slow = slow.next;
            temp=temp.next;
        }
        System.out.println(slow.key);
        Node taret= slow.next;
        slow.next=taret.next;
        taret.next=null;

        root=head.next;

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
