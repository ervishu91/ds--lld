package com.practice.ds.practic;

import java.util.Arrays;

public class ReverseLL {

    static public class Node{
        public int key;
        public Node next=null;
        public Node(int key){
            this.key=key;
        }
    }
    public static void main(String[] args) {

        Node root = new Node(1);
        root.next=new Node(2);
        root.next.next = new Node(3);
        System.out.println("reverseList is "+ Arrays.toString(print(reverseLLRecursive(root,null))));
    }

    private static Node reverseLLRecursive(Node root, Node prev) {
        if (root == null) {
            return prev;
        }

        Node next = root.next;
        root.next = prev;

        return reverseLLRecursive(next, root);
    }

    private static Node reverseLL(Node root) {
        Node prev=null,current=root,next=null;
        while(current!=null){
            next = current.next;
            current.next=prev;
            prev=current;
            current=next;
        }
        return prev;
    }

    private static int[] print(Node root){
        int[] ll= new int[3];
        int i=0;
        while(root!=null){
            ll[i++]=root.key;
            root=root.next;
        }
        return ll;
    }
}
