package com.practice.ds.BinarySearch;

public class MinCapcityTocarryCargoInKdays {
    /*fjdjdk
    fdjdjdj
    djdkkdkdkdk

            */

    static int minCapcityTocarryCargoInKdays(int[] weights,int k){
        if (weights == null || weights.length == 0 || k < 1) {
            throw new IllegalArgumentException("Weights must not be empty and days must be positive");
        }

        int minCap=0,maxCap=0;
        for(int num : weights){
            minCap= Math.max(minCap,num);
            maxCap+=num;
        }
        while(minCap<maxCap){
            int midCap = minCap+(maxCap-minCap)/2;
            int daysReq = getDaysReqToShip(weights, midCap);

            if(daysReq==k){
                // Valid capacity; continue left to find the minimum one.
                maxCap = midCap - 1;
            }else if(daysReq<k){
                // This capacity works, so search for a smaller valid capacity.
                maxCap = midCap - 1;
            }else{
                // More than k days are needed, so capacity is too small.
                minCap = midCap + 1;
            }
        }

        // minCap is the smallest capacity that can finish within k days.
        return minCap;
    }

    private static int getDaysReqToShip(int[] weights, int cap) {
        int daysReq = 1;
        int currentLoad = 0;
        for(int num : weights){
            // A package cannot be split, and packages must stay in order.
            if(currentLoad + num > cap){
                daysReq++;
                currentLoad = 0;
            }
            currentLoad += num;
        }
        return daysReq;
    }

    public static void main(String[] args) {
        System.out.println("min capacity is : "+minCapcityTocarryCargoInKdays(new int[]{1,2,3,4,5,6,7,8,9,10},5));
    }
}
