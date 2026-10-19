package com.practice.ds.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MinFuelStation {
    public static void main(String[] args) {
        int target=100;
        int[][] stations ={
                {10,60},
                {20,30},
                {30,30},
                {60,40}};
        System.out.println("min no of station req:"+minStationreq(10,target,stations));
    }

    private static int minStationreq(int fuel,int target, int[][] stations) {
        int station = 0;
        int position=0,n= stations.length;
        Arrays.sort(stations, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.reverseOrder());
        for(int i=0;i<n;i++){
            if(stations[i][0]<=fuel){
                heap.offer(stations[i][1]);
            }

        }

        return station;
    }
}
