package com.practice.ds.dp;

import java.util.Arrays;
import java.util.Comparator;

public class MaxProfitByJobScheduling {

    public static void main(String[] args) {
        System.out.println("max profit is ::"+maxProfitByJobScheduling(new int[][]{{1, 3, 50},
                {2, 5, 20},
                {4, 6, 70},
                {6, 7, 60},
                {5, 8, 30}}));
    }

    private static int maxProfitByJobScheduling(int[][] jobs) {

        int max=0, n= jobs.length;

        Arrays.sort(jobs,(a,b)->a[1]-b[1]);
        int[] dp= new int[n+1];
        dp[0]=0;
        for(int i=0;i<n;i++){
            dp[i+1] = Math.max(dp[i],jobs[i][2]);
            int prevJob = findPrevJob(jobs, i);
            if(prevJob!=-1)
                dp[i+1] = Math.max(dp[i+1],dp[prevJob+1]+jobs[i][2]);
        }

        return dp[n];
    }

    private static int findPrevJob(int[][] jobs, int k) {

        int left=0,right = k-1,ans=-1;
        while(left<=right){
            int mid = left + (right-left)/2;
            if(jobs[mid][1]<=jobs[k][0]){
                left = mid+1;
                ans = mid;
            }
            else{
                right = mid-1;
            }
        }
        return ans;
    }
}
