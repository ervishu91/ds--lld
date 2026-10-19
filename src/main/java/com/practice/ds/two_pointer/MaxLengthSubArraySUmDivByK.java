package com.practice.ds.two_pointer;

import java.util.HashMap;
import java.util.Map;

public class MaxLengthSubArraySUmDivByK {
    public static void main(String[] args) {
        System.out.println("max length is ::"+maxLengthSubArraySUmDivByK(new int[]{4, 5, 0, -2, -3, 1},5));
    }

    private static int maxLengthSubArraySUmDivByK(int[] arr, int k) {
        int max =0;
        Map<Integer,Integer> prefixMap = new HashMap<>();
        prefixMap.put(0,-1);
        int prefix=0;
        for(int i=0;i<arr.length;i++){
            int num = arr[i];
            prefix+=num;
            if(prefix<0)
                prefix+=((prefix % k) + k);
            prefix%=k;
            if(prefixMap.containsKey(prefix)){
                int left = prefixMap.get(prefix);
                max = Math.max(max,i-left );
            }
            prefixMap.putIfAbsent(prefix,i);

        }

        return max;
    }
}
