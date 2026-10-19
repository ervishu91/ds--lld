package com.practice.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeInterval {
    public static void main(String[] args) {

        int[][] intervals = {
                {1, 4},
                {0, 2},
                {3, 5}
        };
        System.out.println("intervals to be  merge ");
        Arrays.stream(intervals).forEach(k-> System.out.println(Arrays.toString(k)));
        System.out.println("after merge :");
        int[][] merged = mergeInterval(intervals);
        Arrays.stream(merged).forEach(k-> System.out.println(Arrays.toString(k)));
    }
    private static int[][] mergeInterval(int[][] intervals){
        int n = intervals.length;
        List<int[]> merged = new ArrayList<>();
        if(n<=1)
            return intervals;
        Arrays.sort(intervals,(a,b)-> Integer.compare(a[0],b[0]));
        int[] prev= intervals[0];
        int[] next;
        for(int i=1;i<n;i++){
            next= intervals[i];
            if(next[0]<=prev[1]){
                prev[1]= Math.max(prev[1],next[1]);
            }else{
                merged.add(prev);
                prev=next;
            }

        }
        merged.add(prev);
        return merged.toArray(new int[merged.size()][2]);
    }
}
