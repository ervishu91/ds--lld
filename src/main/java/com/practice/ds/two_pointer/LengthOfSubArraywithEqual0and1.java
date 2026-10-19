package com.practice.ds.two_pointer;

import java.util.HashMap;
import java.util.Map;

public class LengthOfSubArraywithEqual0and1 {
    public static void main(String[] args) {
        System.out.println("len of subarray::"+ lengthOfSubArraywithEqual0and1(new int[] {0,0,1,0,0,0,1,1}));
    }

    private static int lengthOfSubArraywithEqual0and1(int[] arr) {
        int prefix=0,len=0;
        Map<Integer,Integer> balanceMap = new HashMap<>();
        balanceMap.put(0,-1);
        int balance=0;
        for(int i=0;i<arr.length;i++){
            if(arr[i]==1)
                prefix++;
            balance = prefix-((i+1) - prefix);
            if(balanceMap.containsKey(balance))
                len = Math.max(len, i-balanceMap.get(balance));
            balanceMap.putIfAbsent(balance,i);
        }

        return len;
    }
}
