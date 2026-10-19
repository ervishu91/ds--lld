package com.practice.ds.graph;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PrimsAlogorithm {

    public static void main(String[] args) {
        System.out.println("min cost for mst is:"+primsAlogorithm(new int[][]{
                {0, 0},
                {2, 2},
                {3, 10},
                {5, 2},
                {7, 0}
        }));
    }

    private static int primsAlogorithm(int[][] points) {
        int cost=0;
        int n = points.length;
        int[] dist = new int[n];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[0]=0;
        Set<Integer> connectedPoints= new HashSet<>();
        connectedPoints.add(0);
        int minPoint=0;
        int mindist=Integer.MAX_VALUE;
        while(connectedPoints.size()<n){

            int temopoint=-1;
            mindist=Integer.MAX_VALUE;
            for(int i = 0;i<n;i++){

                if(!connectedPoints.contains(i)){
                    int newDist = Math.abs(points[minPoint][0]-points[i][0]) + Math.abs(points[minPoint][1]-points[i][1]);
                    dist[i] = Math.min(dist[i],newDist);
                    if(dist[i]<mindist){
                        mindist = dist[i];
                        temopoint = i;
                    }
                }
            }
            cost+=mindist;
            connectedPoints.add(temopoint);
            minPoint=temopoint;

        }
        return cost;
    }
}
