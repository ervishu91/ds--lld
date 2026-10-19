package com.practice.ds.prefix;

import java.util.HashMap;
import java.util.Map;

public class SubararyCountSumEqualK {

    public static void main(String[] args) {
        System.out.println("subarray count sum equal to k::"+subararyCountSumEqualK(new int[]{1,2,3},3));
    }

    private static int subararyCountSumEqualK(int[] nums, int k) {
        int count=0;
        Map<Integer,Integer> prefixFreqMap = new HashMap<>();
        prefixFreqMap.put(0,1);
        int prefix=0;
        for(int num : nums){
            prefix+=num;
            if(prefixFreqMap.containsKey((prefix-k))){
                count+=prefixFreqMap.get(prefix-k);
            }
            prefixFreqMap.put(prefix,prefixFreqMap.getOrDefault(prefix,0)+1);
        }

        return count;
    }
}
