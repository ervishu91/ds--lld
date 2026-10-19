package com.practice.interview;

public class LonegstMountain {
    public static void main(String[] args) {
        System.out.println("longest mountain is ::"+lonegstMountain(new int[]{1, 4, 2, 3, 6, 4}));
    }

    private static int lonegstMountain(int[] nums) {
        int maxheight=0;

        int up=0,down=0,n=nums.length,start=0;
        for(int i=1;i<n;i++){
            if(nums[i]>nums[i-1]){
                if(up<=0 || down>0)
                    start=i-1;
                up=1;
                down=0;


            }else{
                if(nums[i]==nums[i-1]){
                    start=i;
                    up=0;
                    down=0;
                }else {
                    if (up > 0) {
                        down = 1;
                    } else {
                        start = i;
                        up = 0;
                        down = 0;
                    }
                }
            }
            if(up>0 && down>0){
                maxheight= Math.max(maxheight,i-start+1);
            }
        }


        return maxheight;
    }
}
