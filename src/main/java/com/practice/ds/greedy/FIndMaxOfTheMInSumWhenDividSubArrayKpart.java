package com.practice.ds.greedy;

public class FIndMaxOfTheMInSumWhenDividSubArrayKpart {
    public static void main(String[] args) {
        System.out.println("max sum is :"+fIndMaxOfTheMInSumWhenDividSubArrayKpart(new int[]{7,2,5,10,8},2));
    }

    private static int fIndMaxOfTheMInSumWhenDividSubArrayKpart(int[] arr, int k) {
        int sum=0,n = arr.length;
        int min=0,max=0;
        for(int num: arr){
            min = Math.max(min,num);
            max+=num;
        }

        while(min<max){
            int mid = min + (max-min)/2;
            if(partspossible(arr,mid,k)){
                max=mid;
            }else{
                min=mid+1;
            }
        }

        return min;
    }

    private static boolean partspossible(int[] arr, int mid, int k) {
        int temp=0;
        int part=1;
        for(int num: arr){
            if(temp+num>mid){
                part++;
                temp=num;
            }else{
                temp+=num;
            }

        }
        return part<=k;
    }
}
