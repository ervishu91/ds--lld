package com.practice.ds.dp;

import java.util.*;

public class MaxColorFreqInThePossiblePathWithNoCycle {

    public static void main(String[] args) {
        System.out.println("max color is ::"+maxColorFreqInThePossiblePathWithNoCycle("abcaa",new int[][]{
                {0, 1},
                {1, 2},
                {2, 3},
                {3, 4}
        }));
    }

    private static int maxColorFreqInThePossiblePathWithNoCycle(String colors, int[][] edges) {
        int maxcol=0;
        int n = colors.length();
        int[][] dp = new int[26][n];
        int[] inDegree = new int[n];
        Map<Integer, Set<Integer>> graph= new HashMap<>();

        Queue<Integer> que = new LinkedList<>();
        for(int[] edge : edges){
            int u= edge[0];
            int v = edge[1];
            if (graph.computeIfAbsent(u, set-> new HashSet<>()).add(v))
                inDegree[v]++;
        }
        for(int i=0;i<n;i++){
            if(inDegree[i]==0){
                dp[colors.charAt(i) - 'a'][i] = 1;
                que.offer(i);
            }
        }
        int processedCOl=0;
        while(!que.isEmpty()){
            int node = que.poll();
             processedCOl++;

            for(int dest : graph.getOrDefault(node,Collections.emptySet())){

                for(int i=0;i<26;i++){
                    dp[i][dest] = Math.max(dp[i][dest],dp[i][node]);
                }
                int destColor =  colors.charAt(dest)-'a';
                dp[destColor][dest] = Math.max(dp[destColor][dest],dp[destColor][node]+1);
                maxcol= Math.max(maxcol,dp[destColor][dest]);
                inDegree[dest]--;
                if(inDegree[dest]==0) {
                    que.offer(dest);

                }
            }
        }

        return processedCOl==n?maxcol:-1;
    }
}
