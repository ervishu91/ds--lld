package com.practice.ds.heap;

import java.util.Collections;
import java.util.PriorityQueue;

public class MedianInDataStream {
    public static void main(String[] args) {
        System.out.println("meidan is ::"+median(new int[]{1, 3, 5, 8, 10, 12}));
    }

    public static double median(int[] arr) {
        int n = arr.length;
        PriorityQueue<Integer> minheap  = new PriorityQueue<>();
        PriorityQueue<Integer> maxheap  = new PriorityQueue<>(Collections.reverseOrder());
        double median =1.0;
        for(int i=0;i<n;i++){
            maxheap.offer(arr[i]);
            minheap.offer(maxheap.poll());
            if(minheap.size()-maxheap.size()>1)
                    maxheap.offer(minheap.poll());
        }
        if(maxheap.size()==minheap.size())
        median = (double) (minheap.peek() + maxheap.peek()) /2;
        else median = maxheap.peek();
        return median;
    }
}
