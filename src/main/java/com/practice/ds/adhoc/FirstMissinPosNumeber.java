package com.practice.ds.adhoc;

public class FirstMissinPosNumeber {
    public static void main(String[] args) {
        System.out.println("firsxt + num is ::"+firstMissinPosNumeber(new int[]{3,1,4,2}));
    }

    private static int firstMissinPosNumeber(int[] arr) {
        int n =arr.length;
        for(int i=0;i<n;i++){
            while(arr[i]>=1 && arr[i]<=n && arr[i]!=arr[arr[i]-1]){
                int temp =arr[i];
                arr[i]=arr[temp-1];
                arr[temp-1]= temp;
            }
        }
        for(int i=0;i<n;i++){
            if(i+1!=arr[i]){
                return i+1;
            }
        }
        return n+1;
    }

}
