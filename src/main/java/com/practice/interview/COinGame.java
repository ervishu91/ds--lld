package com.practice.interview;

import java.util.Arrays;

public class COinGame {

    public static void main(String[] args) {
        System.out.println("max sum is ::"+coinGame(new int[]{8, 3, 7, 11}));
    }

    private static int coinGame(int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n][n];
        for(int i=0;i<n;i++){
            Arrays.fill(dp[i],0);
        }
        for(int i=0;i<n;i++){

        }



        return dp[0][n-1];
    }
}
