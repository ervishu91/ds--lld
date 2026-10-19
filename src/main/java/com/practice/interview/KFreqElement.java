package com.practice.interview;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class KFreqElement {

    public static void main(String[] args) {
        System.out.println("k freq elent are "+ Arrays.toString(kFreEle(2,new int[]{4, 4, 4, 6, 6, 7, 7, 7, 7})));
    }

    private static int[] kFreEle(int k, int[] nums) {
        Map<Integer,Integer> freqMap = new HashMap<>();
        int[] ele = new int[k];
        for(int num : nums){
            freqMap.put(num,freqMap.getOrDefault(num,0)+1);
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a,b)-> Integer.compare(freqMap.get(a),freqMap.get(b)));
        for(int num : freqMap.keySet()){
            minHeap.offer(num);
            if(minHeap.size()>k)
                minHeap.poll();
        }
        int i =0;
        while(!minHeap.isEmpty()){
            ele[i++]=minHeap.poll();
        }
        return ele;
    }

}
