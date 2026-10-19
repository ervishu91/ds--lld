package com.practice.ds.dp;

public class MaxSubSequenseSumWothNo2AdjEle {

    public static void main(String[] args) {
        System.out.println("Max sum is ::"+ maxSubSequenseSumWothNo2AdjEle(new int[]{2, 1, 4, 9}));
    }

    private static int maxSubSequenseSumWothNo2AdjEle(int[] arr) {
        int n =arr.length;
        int[] dp = new int[n];
        dp[0] = arr[0];
        for(int i=1;i<n;i++){
            if(i<=1)
                dp[1] = Math.max(dp[0],arr[1]);
            else
                dp[i] = Math.max(dp[i-1],dp[i-2]+arr[i]);
        }

        return dp[n-1];
    }
}
