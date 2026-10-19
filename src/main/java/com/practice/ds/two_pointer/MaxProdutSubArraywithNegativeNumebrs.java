package com.practice.ds.two_pointer;

public class MaxProdutSubArraywithNegativeNumebrs {
    public static void main(String[] args) {
        System.out.println("max length is ::"+ maxProdutSubArraywithNegativeNumebrs(new int[] {2,3,-2,4}));
    }

    private static int maxProdutSubArraywithNegativeNumebrs(int[] arr) {

        int n = arr.length;
        int max = arr[0];
        int min = arr[0];
        int answer = arr[0];

        for (int i = 1; i < n; i++) {

            if (arr[i] < 0) {
                int temp = max;
                max = min;
                min = temp;
            }

            max = Math.max(arr[i], arr[i] * max);
            min = Math.min(arr[i], arr[i] * min);

            answer = Math.max(answer, max);
        }

        return answer;
    }
}
