package com.practice.ds.practic;

import java.util.*;

public class ZigZagorderTraversal {


    static class TreeNode {
        int val;
        TreeNode left=null;
        TreeNode right =null;
        TreeNode(int val){
            this.val=val;
        }
    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        System.out.println("zigzgorder traversal is "+ Arrays.deepToString(zigZagorderTraversal(root)));
    }

    private static int[][] zigZagorderTraversal(TreeNode root) {
        if (root == null) {
            return new int[0][];
        }
        List<List<Integer>> zigZagorder = new ArrayList<>();
        Queue<TreeNode> que = new LinkedList<>();
        que.add(root);
        boolean reverse=false;
        while(!que.isEmpty()){
            int size = que.size();
            List<Integer> level = new ArrayList<>();
            while(size-->0){
                TreeNode node = que.poll();
                if(node.left!=null)
                    que.add(node.left);
                if(node.right!=null)
                    que.add(node.right);
                level.add(node.val);
            }
            if(reverse)
                Collections.reverse(level);
            zigZagorder.add(level);
            reverse = !reverse;

        }

        int[][] result = new int[zigZagorder.size()][];

        for (int i = 0; i < zigZagorder.size(); i++) {
            result[i] = zigZagorder.get(i)
                    .stream()
                    .mapToInt(Integer::intValue)
                    .toArray();
        }

        return result;

    }
    private static String routingLinear(String[] interfaces, String ip) {
        int bits=0;
        String nwid="0.0.0.0";
        String[] ipOctet = ip.split("\\.");
        for(String inter : interfaces){
            String[] parts = inter.split("/");
            int interNwBits= Integer.parseInt(parts[1]);
            int completeByte= interNwBits/8;
            int partialByte= interNwBits%8;
            String[] octet = parts[0].split("\\.");
            boolean match = true;
            for(int i=0;i<completeByte;i++){
                if(Integer.parseInt(ipOctet[i])!=Integer.parseInt(octet[i])){
                    match=false;
                    break;
                }
            }
            if(partialByte>0){
                int mask = 0XFF << (8-partialByte) & 0XFF;
                int ipByte = Integer.parseInt(ipOctet[completeByte]);
                int interbyte=Integer.parseInt(octet[completeByte]);
                if( (ipByte & mask)!= (interbyte & mask))
                {
                    match=false;
                }
            }
            if(match && interNwBits>bits){
                bits=interNwBits;
                nwid = inter;
            }
        }

        return nwid;
    }

}
