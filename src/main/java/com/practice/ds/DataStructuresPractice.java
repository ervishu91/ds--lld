package com.practice.ds;

import java.util.Arrays;

/** Entry point for small data-structure practice examples. */
public final class DataStructuresPractice {
    private DataStructuresPractice() {
    }

    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>(5);
        stack.push(10);
        stack.push(20);

        int[] values = {3, 7, 11, 19, 24};
        System.out.println("Stack pop: " + stack.pop());
        System.out.println("Binary search index: " + Arrays.binarySearch(values, 19));
    }
}
