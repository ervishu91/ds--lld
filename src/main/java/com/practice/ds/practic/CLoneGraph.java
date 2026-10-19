package com.practice.ds.practic;

import java.util.*;


public class CLoneGraph {

    public static class Node {
        public int val;
        public List<Node> neighbors;

        public Node(int val) {
            this.val = val;
            this.neighbors = new ArrayList<>();
        }
    }

    public static void main(String[] args) {

        Node root = new Node(6);
        Node  cloneRoot= cloneGraph(root);
        System.out.println();
    }
    public static Node cloneGraph(Node root){
    Map<Node,Node> cloneMap = new HashMap<>();

        Queue<Node> que = new ArrayDeque<>();
        que.add(root);
        while(!que.isEmpty()){
            Node curr = que.poll();
            if(cloneMap.get(curr)==null){
                cloneMap.put(curr,new Node(curr.val));
                que.addAll(curr.neighbors);
            }
        }
        for(Node key : cloneMap.keySet()){
            Node cloned = cloneMap.get(key);
            for(Node near : key.neighbors){
                cloned.neighbors.add(cloneMap.get(near));
            }
        }
    return cloneMap.get(root);

    }
}
