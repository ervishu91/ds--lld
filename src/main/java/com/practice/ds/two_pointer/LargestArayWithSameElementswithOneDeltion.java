package com.practice.ds.two_pointer;

import java.util.Arrays;

public class LargestArayWithSameElementswithOneDeltion {

    public static void main(String[] args) {
        System.out.println("largestArray len is:"+largestArayWithSameElementswithOneDeltion(new int[]{1,0,1,0,0}));
    }

    private static int largestArayWithSameElementswithOneDeltion(int[] nums) {
        int len =0,n=nums.length;
        int left=0;
        int[] arr= new int[2];
        for(int i=0;i<n;i++){
            arr[nums[i]]++;
            while(arr[0]>1 && arr[1]>1){
                System.out.println("left"+left+",i:"+i+"arr:"+ Arrays.toString(arr));
                arr[nums[left]]--;
                left++;

            }
            len = Math.max(len,i-left);

        }

        return len;
    }
}
