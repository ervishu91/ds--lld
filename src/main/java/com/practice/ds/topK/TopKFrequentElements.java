package com.practice.ds.topK;

import java.util.*;

public class TopKFrequentElements {

    static List<Integer> topKFrequentElements(int[] list, int k){

        List<Integer> repList = new ArrayList<>();
        if(list.length<=0)
        return repList;

        Map<Integer,Integer> freqMap = new HashMap<>();
        PriorityQueue<Integer> minHeap  = new PriorityQueue<>(Comparator.comparingInt(freqMap::get));
        for(int num : list){
            freqMap.put(num, freqMap.getOrDefault(num,0)+1);
        }
        for( int key: freqMap.keySet()){
            minHeap.offer(key);
            if(minHeap.size()>k)
               minHeap.poll();
        }
        while(!minHeap.isEmpty())
                repList.add(minHeap.poll());

  return repList;
    }

    public static void main(String... args){
        System.out.println("top k element"+topKFrequentElements(new int[]{-1,-1,-1,2,2,3,3,3,3},2));
    }
}
