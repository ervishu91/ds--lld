package com.practice.ds.graph;

public class GasStation {
    public static void main(String[] args) {
        System.out.println("start station is ::" + gasStation(new int[]{1,2,3,4,5}, new int[]{3,4,5,1,2}));
    }

    private static int gasStation(int[] gas, int[] cost) {
        int total=0,tank=0,start=0, n = gas.length;

        for(int i=0;i<n;i++){
            int diff = gas[i]-cost[i];
            total+=diff;
            tank+=diff;
            if(tank<0){
                start=i+1;
                tank=0;
            }
        }
        if(total<0)
            return -1;
        return start;
    }
}
