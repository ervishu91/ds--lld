package com.practice.ds.practic;

import java.util.HashMap;
import java.util.Map;

public class NoOfSubArraySumEqualK {

    public static void main(String[] args) {
        System.out.println("no of sub aray :"+noOfSubArraySumEqualK(new int[]{1,1,1}, 2));
    }

    private static int noOfSubArraySumEqualK(int[] nums,int k) {
        int prefix=0,numOfArr=0;
        Map<Integer,Integer> freqMap = new HashMap<>();
        freqMap.put(0,1);
        for(int num : nums){
            prefix+=num;
            if(freqMap.containsKey(prefix-k)){
                numOfArr+=freqMap.get(prefix-k);
            }
            freqMap.put(prefix, freqMap.getOrDefault(prefix,0)+1);
        }
        return numOfArr;

    }
}
