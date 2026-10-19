package com.practice.ds.Monotonic;

import java.util.Stack;

public class LargestHistogram {
    public static void main(String[] args) {
        System.out.println("largestArea::"+largestHistogram(new int[]{2, 4, 5, 6, 3}));
    }

    private static int largestHistogram(int[] nums) {
        int n = nums.length,area=0;
        Stack<Integer> stack = new Stack<>();
        for(int i =0;i<=n;i++){
            int curr  = i==n?0:nums[i];
            while(!stack.isEmpty() &&  curr<nums[stack.peek()]){
                int rightSide = stack.pop();
                int height = nums[rightSide];
                int width = i-rightSide;
                area = Math.max(area,height*width);

            }
            if(i<n)
                stack.push(i);
        }
        return area;
    }
}
