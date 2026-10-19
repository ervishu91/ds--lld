package com.practice.ds.practic;

import java.util.PriorityQueue;

public class KthlargetEleemnt {
    public static void main(String[] args) {
        System.out.println("kth largest element is ::"+ kthlargetEleemnt(new int[]{3,2,1,5,6,4},2));
    }

    private static int kthlargetEleemnt(int[] nums, int k) {

        int kmx=0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for(int num : nums){
            minHeap.offer(num);
            if(minHeap.size()>k)
                    minHeap.poll();
        }



        return minHeap.peek();
    }
}
