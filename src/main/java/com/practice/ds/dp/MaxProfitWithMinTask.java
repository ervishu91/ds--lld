package com.practice.ds.dp;

import java.sql.SQLOutput;
import java.util.Arrays;

public class MaxProfitWithMinTask {

       public static void main(String[] args) {
        System.out.println("maxprofitwithMinTask"+getMaxprofitWithMinTask(new int[][]{
                {1, 3, 50},
                {2, 5, 20},
                {4, 6, 70},
                {6, 8, 40},
                {5, 7, 60}
        }));
    }

    private static int getMaxprofitWithMinTask(int[][] tasks) {

        int n = tasks.length;

        Arrays.sort(tasks, (a, b) ->
                Integer.compare(a[1], b[1])
        );

        // dp[i][0] = max profit using first i tasks
        // dp[i][1] = min number of tasks for that profit
        int[][] dp = new int[n + 1][2];

        for (int i = 1; i <= n; i++) {

            // Skip current task
            dp[i][0] = dp[i - 1][0];
            dp[i][1] = dp[i - 1][1];

            // Try taking current task with every compatible previous task
            for (int j = 0; j < i - 1; j++) {

                if (tasks[i - 1][0] >= tasks[j][1]) {

                    int takeProfit =
                            dp[j + 1][0] + tasks[i - 1][2];

                    int takeCount =
                            dp[j + 1][1] + 1;

                    // Take if profit is better,
                    // or same profit with fewer tasks
                    if (takeProfit > dp[i][0]
                            || (takeProfit == dp[i][0]
                            && takeCount < dp[i][1])) {

                        dp[i][0] = takeProfit;
                        dp[i][1] = takeCount;
                    }
                }
            }
        }

        return dp[n][0];
    }
}
