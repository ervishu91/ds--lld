package com.practice.ds.dp;

import java.util.Arrays;

public class LIS {
    public static void main(String[] args) {
        System.out.println("LIS length is ::"+lis(new int[]{10,9,2,5,3,7,101,18}));
    }

    private static int lis(int[] arr) {
    int n=arr.length;
    int[] dp = new int[n];
    Arrays.fill(dp,1);
    for(int i=1;i<n;i++){
        for(int j=0;j<i;j++){
            if(arr[i]>arr[j])
                dp[i] = Math.max(dp[i],dp[j]+1);
        }
    }

    return Arrays.stream(dp).max().getAsInt();
    }
}
