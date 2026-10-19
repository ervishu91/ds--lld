package com.practice.ds;

public class Kokobanana {
    static int findMinSpeedForKHours(int[] piles, int h ){
        int low =1;
        int high =1;
        for(int num : piles){
            high = Math.max(high,num);
        }
        while(low<high){
            int mid = low +(high-low)/2;
            System.out.println("low::"+low+"high::"+high+"mid::"+mid);
            int reqTime = getTimeForSpeed(piles,mid);
            if(reqTime<=h){
                high = mid;
            }
            else{
                low= mid+1;
            }
        }

        return low;

    }
    //30 speed=4
    //8,

    private static int getTimeForSpeed(int[] piles, int mid) {
        int time =0;
        for(int pile : piles){
            time+= (pile+mid-1)/mid;
        }
        System.out.println("mid::"+mid+"time::"+time);
        return time;

    }

    public static void main(String[] args) {
        System.out.println("min speed is "+findMinSpeedForKHours(new int[]{30,11,23,4,20},6));
    }
}
