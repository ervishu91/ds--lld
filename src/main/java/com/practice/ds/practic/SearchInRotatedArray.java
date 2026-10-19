package com.practice.ds.practic;

public class SearchInRotatedArray
{

    public static void main(String[] args) {
        System.out.println("search eleemnt is at index:" +searchInRotatedArray(new int[]{3,4,5,1,2},2));
    }

    private static int searchInRotatedArray(int[] nums, int k) {
        int left=0,right = nums.length-1;
        while(left<=right){
            int mid = left +(right-left)/2;
            if(nums[mid]==k)
                return mid;
            if(nums[left]<=nums[mid]){
                if(k>=nums[left] && k<nums[mid]){
                    right=mid-1;
                }else{
                    left=mid+1;
                }
            }else{
                if(k<=nums[right] && k>nums[mid]){
                   left=mid+1;
                }else{
                    right=mid-1;
                }
            }
        }




      return -1;
    }
}
