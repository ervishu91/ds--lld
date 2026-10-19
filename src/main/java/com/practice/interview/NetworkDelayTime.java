package com.practice.interview;

import java.util.*;

public class NetworkDelayTime {
    public static void main(String[] args) {
        int[][] times = {
                {0, 1, 10},
                {0, 2, 2},
                {2, 1, 1},
                {1, 3, 2},
                {2, 3, 10}
        };

        int n = 4;
        int k = 0;
        System.out.println("NetworkDelayTime::"+networkDelayTime(n,k,times));
    }

    private static int networkDelayTime(int n, int k, int[][] times) {
        int[] dist = new int[n];
        Arrays.fill(dist,Integer.MAX_VALUE);
        int maxTime=0;
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b)->Integer.compare(a[1],b[1]));
        minHeap.offer(new int[]{k,0});
        dist[k]=0;
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for(int[] edge : times){
            graph.computeIfAbsent(edge[0],list->new ArrayList<>()).add(new int[]{edge[1],edge[2]});
        }

        while(!minHeap.isEmpty()){
            int[] curr = minHeap.poll();
            if(dist[curr[0]]<curr[1])
                continue;
            for(int[] edges : graph.getOrDefault(curr[0],Collections.emptyList())){
                int toEdge= edges[0];
                int weight = edges[1];
                int currDist = dist[toEdge];
                if(currDist>curr[1]+weight){
                    dist[toEdge] = curr[1]+weight;
                    minHeap.offer(new int[]{toEdge,dist[toEdge]});
                }
            }
        }
        for(int dis: dist){
            maxTime = Math.max(maxTime,dis);
        }
    return maxTime==Integer.MAX_VALUE?-1:maxTime;
    }
}
