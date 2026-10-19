package com.practice.ds.practic;

public class HouseRobber {
    public static void main(String[] args) {
        System.out.println("max money after stealing is "+houseRobber(new int[]{2, 7, 9, 3, 1}));
    }

    private static int houseRobber(int[] house) {
        int n = house.length;
        int[] dp = new int[n+1];
        dp[0]=0;
        for(int i=0;i<n;i++){
           // dp[i+1]=dp[i];
            dp[i+1] = Math.max(dp[i],house[i]+(i>1?dp[i-1]:0));
        }
        return dp[n];
    }
}
