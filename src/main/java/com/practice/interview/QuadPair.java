package com.practice.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuadPair {

    static List<int[]> quaddruple(int[] arr,int target){
        int n = arr.length;
        Arrays.sort(arr);
        List<int[]> ans = new ArrayList<>();
        for(int i =0;i<n-3;i++){
            int a = arr[i];

            for(int j =i+1;j<n-2;j++){
                int b = arr[j];
                int rem = target - (a+b);
                int left = j+1,right=n-1;
                while(left<right){
                    int c = arr[left];
                    int d = arr[right];
                    int pairSum = c+d;
                    if(pairSum== rem){
                        ans.add(new int[]{a,b,arr[left],arr[right]});
                        System.out.println(a+","+b+","+arr[left]+","+arr[right]);
                        left++;
                        right--;

                        while(arr[left]==c)
                             left++;
                        while(arr[right]==c)
                            right--;

                    }
                    else if(pairSum<rem){
                        left++;
                    }
                    else {
                        right--;
                    }
                }
                while(arr[++j]==b);
                j--;

            }
            while(arr[++i]==a);
            i--;

        }
        return ans;
    }

    public static void main(String[] args) {
        List<int[]> quaddruple = quaddruple(new int[]{2,2,2,2,2}, 8);
        System.out.println("soutions::"+quaddruple);
    }
}
