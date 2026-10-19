package com.practice.interview;

import java.util.Comparator;
import java.util.PriorityQueue;

import static com.practice.ds.heap.MedianInDataStream.median;

public class Median {


    static PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    static PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());


    public static void main(String[] args) {
       int[] ele={1,4,2,5,8,3,5,6};
       for(int i :ele)
       {
           insertElement(i);
           findMedian();
       }

    }

    private static void findMedian() {
        if(maxHeap.size()>minHeap.size()){
            System.out.println("medina is "+ maxHeap.peek());
        }else{
            System.out.println("medina is "+ ((double)(maxHeap.peek()+minHeap.peek())/2));
        }
    }

    private static void insertElement(int i) {
        maxHeap.add(i);
        minHeap.add(maxHeap.poll());
        if(minHeap.size()>maxHeap.size()){
            maxHeap.add(minHeap.poll());

        }

    }
}
