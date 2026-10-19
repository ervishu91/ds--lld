package com.practice.ds.Monotonic;

import java.util.Deque;
import java.util.LinkedList;

public class MinSubArraySumAtLeastK {
    public static void main(String[] args) {
        System.out.println("min length is ::"+minSubArraySumAtLeastK(new int[]{84, -37, 32, 40, 95},167));
    }

    private static int minSubArraySumAtLeastK(int[] arr, int k) {
        int n =arr.length;
        int min =n+1;

        Deque<Integer> deque = new LinkedList<>();
        int[] prefix = new int[n+1];
        int pf=0;
        prefix[0]=0;
        for(int i=1;i<=n;i++){
            pf+=arr[i-1];
            prefix[i]=pf;

        }
        for(int i=0;i<=n;i++){
            while (!deque.isEmpty() && prefix[i]-prefix[deque.peekFirst()]>=k){
                min = Math.min(min,i-deque.removeFirst());
            }
            while(!deque.isEmpty() && prefix[i]<prefix[deque.peekLast()]){
                deque.removeLast();
            }
            deque.addLast(i);
        }
        return min;
    }
}
