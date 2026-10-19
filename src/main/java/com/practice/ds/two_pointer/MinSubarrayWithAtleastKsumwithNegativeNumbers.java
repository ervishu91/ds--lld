package com.practice.ds.two_pointer;

import java.util.ArrayDeque;
import java.util.Deque;

public class MinSubarrayWithAtleastKsumwithNegativeNumbers {
    public static void main(String[] args) {
        System.out.println("min len is "+minLengthWithSumAtleastK(new int[]{2,-1,2},3));
    }

    private static int minLengthWithSumAtleastK(int[] arr, int k) {

        int n = arr.length;
        int len = Integer.MAX_VALUE;

        int[] prefix = new int[n + 1];
        Deque<Integer> deque = new ArrayDeque<>();
        deque.addLast(0);

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + arr[i];

            // Remove dominated prefix sums from the back.
            while (!deque.isEmpty()
                    && prefix[deque.peekLast()] >= prefix[i + 1]) {
                deque.removeLast();
            }

            // Every valid front gives a candidate subarray.
            while (!deque.isEmpty()
                    && prefix[i + 1] - prefix[deque.peekFirst()] >= k) {

                int j = deque.removeFirst();

                len = Math.min(len, i + 1 - j);
            }

            deque.addLast(i + 1);
        }

        return len == Integer.MAX_VALUE ? -1 : len;
    }
}
