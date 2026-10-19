package com.practice.ds.two_pointer;

public class TrappingRainWater {
    public static void main(String[] args) {
        System.out.println("trpped rain water is ::"+ trappingRainWater(new int[]{4,2,0,3,2,5}));
    }

    private static int trappingRainWater(int[] height) {
        int trapped=0;
        int leftmax=0;
        int rightmax=0;
        int left=0;
        int right = height.length-1;
        while(left<=right){
            if(leftmax<=rightmax){
                if(leftmax<height[left]){
                    leftmax= height[left];
                }else{
                    trapped+= leftmax-height[left];
                }
                left++;
            }
            else{
                if(rightmax<height[right]){
                    rightmax= height[right];
                }else{
                    trapped+= rightmax-height[right];
                }
                right--;
            }
        }

        return trapped;
    }
}
