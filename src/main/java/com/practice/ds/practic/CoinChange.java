package com.practice.ds.practic;

import java.util.Arrays;

public class CoinChange {

    public static void main(String[] args) {
        System.out.println("min no of coin req::"+coinChange(8, new int[]{1, 2, 5}));
        System.out.println("min no of coin req::"+coinChangeByRecur(8, new int[]{1, 2, 5},0));

    }

    private static int coinChange(int sum, int[] coins) {
        int[] dp = new int[sum+1];
        Arrays.fill(dp,sum+1);
        dp[0]=0;
        for(int i=1;i<=sum;i++){
            for(int coin : coins){
                if(i>=coin){
                    dp[i]=Math.min(dp[i],1+dp[i-coin]);
                }
            }
        }
       return dp[sum];
    }
    private static int coinChangeByRecur(int sum, int[] coins, int no) {
        if (sum == 0)
            return no;

        int coinsneed = sum + 1;

        for (int coin : coins) {
            if (sum >= coin) {
                int coinsreq = coinChangeByRecur(sum - coin, coins, no + 1);

                if (coinsreq != -1)
                    coinsneed = Math.min(coinsneed, coinsreq);
            }
        }

        return coinsneed == sum + 1 ? -1 : coinsneed;
    }
}
