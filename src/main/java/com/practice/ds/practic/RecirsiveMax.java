package com.practice.ds.practic;

public class RecirsiveMax {
    public static void main(String[] args) {
        System.out.println("max is:"+recirsiveMax(new int[]{4,5,3,2,1}));
    }

    private static int recirsiveMax(int[] nums) {
        return maxFromAray(nums,0,nums.length);
    }

    private static int maxFromAray(int[] nums,int current, int length) {
        if(current>=length)
            return Integer.MIN_VALUE;
        int restOftheMax = maxFromAray(nums, current+1,length);
        return Math.max(nums[current],restOftheMax);
    }
}
