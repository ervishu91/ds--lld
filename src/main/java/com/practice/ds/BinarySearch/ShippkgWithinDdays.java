package com.practice.ds.BinarySearch;

public class ShippkgWithinDdays {

    public static void main(String[] args) {
        System.out.println("min capacity req is ::"+ shippkgWithinDdays(new int[]{1,2,3,4,5,6,7,8,9,10},5));
    }

    private static int shippkgWithinDdays(int[] pkg, int k) {
        int left=0,right=0;

        for(int load:pkg){
            left = Math.max(left,load);
            right+=load;
        }

        while(left<right){
            int mid = left+ (right-left)/2;
            int daysReq = countDaysReq(pkg,mid);
            if(daysReq<=k){
                right=mid;
            }
            else{
                left=mid+1;
            }
        }
        return left;
    }

    private static int countDaysReq(int[] pkg, int mid) {
        int days=1,cap=0;
        for(int load:pkg){
            if(cap+load>mid){
                days++;
                cap=load;
            }else{
                cap+=load;
            }
        }
        return days;
    }
}
