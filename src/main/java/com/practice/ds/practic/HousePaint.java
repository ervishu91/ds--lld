package com.practice.ds.practic;

public class HousePaint {
    public static void main(String[] args) {
        System.out.println("min cost for coloring is::"+housePaint(new int[][]{
                {1, 5, 3},
                {2, 9, 4},
                {3, 1, 7},
                {8, 2, 6}
        }));
    }

    private static int housePaint(int[][] colorPrice) {
        int house= colorPrice.length;
        int[][] dp = new int[house][3];
        dp[0][0] = colorPrice[0][0];
        dp[0][1] = colorPrice[0][1];
        dp[0][2] = colorPrice[0][2];
        for(int i=1;i<house;i++){
            for(int j=0;j<3;j++){
                int minOtherColorPrice=Integer.MAX_VALUE;
                for(int k=0;k<3;k++){
                    if(k!=j){
                        minOtherColorPrice = Math.min(minOtherColorPrice,dp[i-1][k]);
                    }
                }
                dp[i][j] = colorPrice[i][j]+minOtherColorPrice;
            }
        }
        int minCOst=Integer.MAX_VALUE;
        for(int k=0;k<3;k++){

            minCOst = Math.min(minCOst,dp[house-1][k]);
            }
        return minCOst;
    }
}
