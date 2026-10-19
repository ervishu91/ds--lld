package com.practice.ds.SlidingWindow;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindowMaxInKwindow {
    public static void main(String[] args) {
        System.out.println("max in K window is  "+ Arrays.toString(slidingWindowMaxInKwindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7},3)));
    }

    private static int[] slidingWindowMaxInKwindow(int[] nums,int k) {
        int n = nums.length;
        int[] output = new int[n-k+1];
        int j=0;
        Deque<Integer> deq = new LinkedList<>();
        for(int i = 0;i<n;i++){
            while (!deq.isEmpty() && deq.peekFirst()<=i-k){
                deq.removeFirst();
            }
            while (!deq.isEmpty() && nums[deq.peekLast()]<=nums[i]){
                deq.removeLast();
            }
            deq.addLast(i);
            if(i>=k-1){
                output[j++] = nums[deq.peekFirst()];
            }


        }


        return output;
    }
}
