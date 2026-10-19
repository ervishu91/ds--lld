package com.practice.ds.BinaryTree;

public class MaximumPathSum {


    static int maxSum=Integer.MIN_VALUE;
    static class Node{
        int key;
        Node left=null;
        Node right=null;
        Node(int key){
            this.key=key;
        }
    }
    public static void main(String[] args) {


        Node root= new Node(5);
        root.left = new Node(4);
        root.right = new Node(8);
        root.right.left= new Node(-3);
        root.right.right= new Node(10);
        root.right.right.left= new Node(2);
        root.right.right.right= new Node(7);
        maximumPathSum(root);

        System.out.println("max Sum path is ::"+ maxSum);
    }

    private static int maximumPathSum(Node root) {
        if(root==null)
            return 0;
        int left = maximumPathSum(root.left);
        int right = maximumPathSum(root.right);
        int max = Math.max(left,0)+Math.max(right,0)+root.key;
        maxSum = Math.max(maxSum,max);

        return root.key + Math.max(left,right);
    }
}
