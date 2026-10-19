package com.practice.ds.practic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

    public static void main(String[] args) {
        System.out.println("three sum triplets are "+ Arrays.deepToString(threeSum(new int[]{-1, 0, 1, 2, -1, -4}, 0)));
    }

    private static int[][] threeSum(int[] nums,int target) {
        List<int[]> triplets= new ArrayList<>();
        int n = nums.length;
        Arrays.sort(nums);
        for(int i=0;i<n-2;i++){
            while(i>0 && nums[i]==nums[i-1]){
                continue;
            }
            int left = i+1,right=n-1;
            int rem = -nums[i];
            while(left<=right){
                int sum = nums[left] + nums[right];
                if(sum<rem){
                    left++;
                }
                if(sum>rem){
                    right--;
                }
                if(sum==rem){
                    triplets.add(new int[]{nums[i],nums[left],nums[right]});
                    left++;
                    right--;
                    while(left<right && nums[left]==nums[left-1])
                        left++;
                    while(left<right && nums[right]==nums[right+1])
                        right--;
                }
            }

        }


        return triplets.toArray(new int[triplets.size()][3]);
    }
}
