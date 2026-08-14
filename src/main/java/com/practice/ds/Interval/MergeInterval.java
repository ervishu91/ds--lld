package com.practice.ds.Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeInterval {

    static int[][] mergeInterval(int[][] interval){
        List<int[]> mergedInterval = new ArrayList<>();
        if(interval.length<=0)
            return new int[][]{};
        Arrays.sort(interval, Comparator.comparingInt(k->k[0]));

        int[] prev = interval[0];
        for(int i=1;i<interval.length;i++){
            int[] next = interval[i];
            if(next[0]<=prev[1]){
                prev[1]  = Math.max(prev[1],next[1]);
            }else{
                mergedInterval.add(new int[]{prev[0],prev[1]});
                prev= next;
            }
        }
        mergedInterval.add(new int[]{prev[0],prev[1]});

        return mergedInterval.toArray(new int[mergedInterval.size()][2]);
    }

    public static  void main(String... args){
        int[][] interval = {{1,3},{2,6},{8,10},{9,12}};
        System.out.println("mergedInterval for :"+interval +"::"+mergeInterval(interval));

    }
}
