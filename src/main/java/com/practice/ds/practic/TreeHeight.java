package com.practice.ds.practic;

public class TreeHeight {

    public static void main(String[] args) {

        LCA.Node root= new LCA.Node(-3);
        root.left= new LCA.Node(5);
        root.right= new LCA.Node(1);
        root.left.left= new LCA.Node(6);
        root.left.right= new LCA.Node(2);
        System.out.println("height of the tree is "+treeheight(root));
    }

    private static int treeheight(LCA.Node root) {
        if(root==null)
            return 0;
        int left = treeheight(root.left);
        int right = treeheight(root.right);

        return 1+Math.max(left,right);

    }
}
