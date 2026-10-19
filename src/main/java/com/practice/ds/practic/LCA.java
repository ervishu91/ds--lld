package com.practice.ds.practic;

import com.practice.lld.VendingMachine.NoPaymentStarted;

public class LCA {

    static class Node{
        int val;
        Node left=null;
        Node right=null;
        Node(int val){
            this.val=val;
        }
    }

    public static void main(String[] args) {
        Node root= new Node(-3);
        root.left= new Node(5);
        root.right= new Node(1);
        root.left.left= new Node(6);
        root.left.right= new Node(2);



        System.out.println("LCS is ::"+lca(root,root.left.left,root.left.right).val);
    }

    private static Node lca(Node root, Node left, Node right) {
        if(root==null || root==left || root==right)
                return root;
        Node leftLCA = lca(root.left,left,right);
        Node rightLCA = lca(root.right,left,right);
        if(leftLCA!=null && rightLCA!=null)
                return root;
        return leftLCA!=null ?leftLCA:rightLCA;

    }
}
