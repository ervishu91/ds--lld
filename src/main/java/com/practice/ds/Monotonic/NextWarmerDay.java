package com.practice.ds.Monotonic;

import java.util.Arrays;
import java.util.Stack;

public class NextWarmerDay {
    public static void main(String[] args) {
        System.out.println("next warmer days are :"+ Arrays.toString(nextWarmerDay(new int[]{73, 74, 75, 71, 69, 72, 76})));
    }

    private static int[] nextWarmerDay(int[] temps) {
        int n = temps.length;
        int[] resp = new int[n];
        Arrays.fill(resp,-1);
        Stack<Integer> tempStack = new Stack<>();
        for(int i=0;i<n;i++){
            while(!tempStack.isEmpty() && temps[i]> temps[tempStack.peek()]){
                int day = tempStack.pop();
                resp[day] = i-day;
            }
            tempStack.push(i);
        }

        return resp;
    }
}
