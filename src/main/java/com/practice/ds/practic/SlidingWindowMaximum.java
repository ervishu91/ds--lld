package com.practice.ds.practic;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindowMaximum {

    public static void main(String[] args) {
        System.out.println("max arrays is:"+ Arrays.toString(slidingWindowMaximum(new int[]{1,3,-1,-3,5,3,6,7},3)));
    }

    private static int[] slidingWindowMaximum(int[] nums, int k) {
        int n = nums.length;
        int[] max= new int[n-k+1];
        int j=0;
        Deque<Integer> deque= new LinkedList<>();
        for(int i=0;i<n;i++){
            int num = nums[i];
            while(!deque.isEmpty() && deque.peekFirst()<=i-k){
                deque.removeFirst();
            }
            while(!deque.isEmpty() && num>nums[deque.peekLast()]){
                deque.removeLast();
            }
            deque.addLast(i);
            if(i>=k-1){
                max[j++]= nums[deque.peekFirst()];
            }
        }
        return max;
    }
}
