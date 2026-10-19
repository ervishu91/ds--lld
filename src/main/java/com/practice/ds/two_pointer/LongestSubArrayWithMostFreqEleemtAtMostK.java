package com.practice.ds.two_pointer;

import java.util.*;

public class LongestSubArrayWithMostFreqEleemtAtMostK {
    public static void main(String[] args) {
        System.out.println("max length is ::"+longestSubArrayWithMostFreqEleemtAtMostK(new int[] {1,2,1,2,1,2,1},2));
    }

    private static int longestSubArrayWithMostFreqEleemtAtMostK(int[] arr, int k) {
        int len =0, n=arr.length;
        int left=0,right=0;
        if(k==0)
                return 0;
        Map<Integer, ArrayDeque<Integer>> freqMap = new HashMap<>();

        while(right<n){
            int ele = arr[right];
            freqMap.computeIfAbsent(ele,list-> new ArrayDeque<>()).addLast(right);
            ArrayDeque<Integer> list = freqMap.get(ele);
            while(!list.isEmpty() && list.getFirst()<left)
                    list.removeFirst();
            if(list.size()>k){
                left = list.removeFirst()+1;
            }
            len = Math.max(len,right-left+1);
            right++;

        }

        return len;
    }
}
