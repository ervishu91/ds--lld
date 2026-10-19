package com.practice.ds.adhoc;

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequence {
    public static void main(String[] args) {
        System.out.println("lonesdt sequence length is ::"+longestConsecutiveSequence(new int[]{100, 4, 200, 1, 3, 2}));
    }

    private static int longestConsecutiveSequence(int[] nums) {
        int len=0;
        Set<Integer> numSet = new HashSet<>();
        for(int num: nums){
            numSet.add(num);
        }
        for(int num : nums){
            if(!numSet.contains(num-1)){
                int currLen=1,sequnce=num+1;
                while(numSet.contains(sequnce)){
                    currLen++;
                    numSet.remove(sequnce);
                    sequnce+=1;
                }
                len = Math.max(len,currLen);
            }
        }

        return len;
    }
}
