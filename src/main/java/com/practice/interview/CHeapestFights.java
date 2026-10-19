package com.practice.interview;

import java.util.*;

public class CHeapestFights {
    public static void main(String[] args) {
        int n = 5;
        int[][] flights = {
                {0, 1, 1},
                {1, 2, 1},
                {0, 2, 5},
                {2, 3, 1},
                {3, 4, 1}
        };

        int src = 0, dst = 4, k = 2;
        System.out.println("cheapest flight::"+ cheapestFights(n,flights,src,dst,k));
    }

    private static int cheapestFights(int n, int[][] flights, int src, int dst, int k) {
        int[] dist = new int[n];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[src]=0;
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for(int[] flight : flights){
            graph.computeIfAbsent(flight[0],list -> new ArrayList<>()).add(new int[]{flight[1],flight[2]});
        }

        for(int i=0;i<=k;i++){
            int[] newDist = dist.clone();
            for(int from =0;from<n;from++){
                if(dist[from]==Integer.MAX_VALUE)
                    continue;
                for(int[] edge : graph.getOrDefault(from,Collections.emptyList())){
                    int to =edge[0];
                    int relaxedPrice = dist[from]+edge[1];
                    if(newDist[to]>relaxedPrice){
                        newDist[to] = relaxedPrice;
                    }
                }
            }
            dist= newDist;
        }

        return dist[dst]==Integer.MAX_VALUE?-1: dist[dst];
    }
}
