package com.practice.interview;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ProductOfArrayItself {

    public static void main(String[] args) {
        System.out.println("array rpoduct except itself is ::"+ Arrays.toString(productOfArrayItself(new int[]{1,2,3,4})));
    }

    private static int[] productOfArrayItself(int[] nums) {
        int n = nums.length;
        int[] product = new int[nums.length];
        Arrays.fill(product,1);
        int left = 1;
        for(int i=0;i<n;i++){
            product[i]=left;
            left*=nums[i];
        }

        int right=1;
        for(int i=n-1;i>=0;i--){
            product[i]*=right;
            right*=nums[i];
        }
        return product;
    }
}
