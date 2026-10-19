package com.practice.ds.greedy;

import java.util.HashMap;
import java.util.Map;

public class LongestSubArraywothSUm0 {
    public static void main(String[] args) {
        //System.out.println("longest array with sum 0 is ::"+longestSubArraywothSUm0(new int[]{1, -1, 3, 2, -2, -3, 3}));
        System.out.println("no of suaary with sum 0 is ::"+NoOfubArraywothSUm0(new int[]{1, -1, 3, 2, -2, -3, 3}));

    }

    private static int longestSubArraywothSUm0(int[] arr) {
        Map<Integer,Integer> map = new HashMap<>();
        int len=0,pre=0;
        map.put(0,-1);
        for(int i=0;i<arr.length;i++){
            pre+=arr[i];
            if(map.containsKey(pre)){
                len = Math.max(len,i-map.get(pre));
            }
            map.putIfAbsent(pre,i);
        }
        return len;
    }
    private static int NoOfubArraywothSUm0(int[] arr) {
        Map<Integer,Integer> map = new HashMap<>();
        int len=0,pre=0;
        map.put(0,1);
        for(int i=0;i<arr.length;i++){
            pre+=arr[i];
            len+= map.getOrDefault(pre,0);
            map.put(pre,map.getOrDefault(pre,0)+1);
        }
        return len;
    }
}
