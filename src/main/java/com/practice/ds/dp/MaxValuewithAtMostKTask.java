package com.practice.ds.dp;

import java.util.Arrays;

public class MaxValuewithAtMostKTask {

    public static void main(String[] args) {
        int[][] tasks = new int[][]{
                {1, 3, 4},
                {2, 4, 5},
                {4, 5, 3},
                {6, 7, 8},
                {5, 8, 10}
        };

        int k = 2;
        System.out.println("max value with k rtask is :"+maxValuewithAtMostKTask(tasks,k) );
    }

    private static int maxValuewithAtMostKTask(int[][] tasks, int k) {
        int value=0, n= tasks.length;
        if(k==0 || n==0)
            return value;
        Arrays.sort(tasks,(a, b)-> Integer.compare(a[1],b[1]));
        int[][] dp = new int[k+1][n+1];
        for(int i=1;i<=k;i++){

            for(int task=0;task<n;task++){
                dp[i][task+1] = dp[i][task];
                int take=tasks[task][2];
                dp[i][task+1] = Math.max(dp[i][task+1],take);
                int j = findLatesttaskendingBefore(tasks,task);
                if(j!=-1)
                    dp[i][task+1] = Math.max(dp[i][task+1],dp[i-1][j+1]+tasks[task][2]);
            }
        }
        for(int i=0;i<=k;i++){
            for(int j=0;j<=n;j++){
                System.out.print(dp[i][j]+",");
            }
            System.out.println();
        }

        return dp[k][n];
    }

    private static int findLatesttaskendingBefore(int[][] tasks, int task) {
        int left=0,right=task-1,ans=-1;
        if(left==right && tasks[left][1]< tasks[task][0])
            return left;
        while(left<right ){
            int mid = left + (right-left)/2;
            if(tasks[mid][1]< tasks[task][0]){
                ans = mid;
                left=mid+1;
            }else  {
                right = mid-1;
            }
        }

        return ans;
    }
}
