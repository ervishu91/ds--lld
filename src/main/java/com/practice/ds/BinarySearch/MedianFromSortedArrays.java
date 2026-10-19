package com.practice.ds.BinarySearch;

public class MedianFromSortedArrays {

    public static void main(String[] args) {
        System.out.println("median is "+medianFromSortedArrays(new int[]{1, 4, 7, 10 ,13, 16, 21},new int[]{2, 3, 5, 6, 8, 9, 11, 12, 14, 15, 17, 20}));
    }

    private static double medianFromSortedArrays(int[] A, int[] B) {

        if (A.length > B.length) {
            int[] temp = A;
            A = B;
            B = temp;
        }

        int n = A.length;
        int m = B.length;

        int leftSize = (n + m + 1) / 2;

        int left = 0;
        int right = n;

        while (left <= right) {

            int partitionA = left + (right - left) / 2;
            int partitionB = leftSize - partitionA;

            int aleft = partitionA == 0 ? Integer.MIN_VALUE : A[partitionA - 1];
            int aright = partitionA == n ? Integer.MAX_VALUE : A[partitionA];

            int bleft = partitionB == 0 ? Integer.MIN_VALUE : B[partitionB - 1];
            int bright = partitionB == m ? Integer.MAX_VALUE : B[partitionB];

            if (aleft <= bright && bleft <= aright) {

                if ((n + m) % 2 == 1) {
                    return Math.max(aleft, bleft);
                }

                return (Math.max(aleft, bleft)
                        + Math.min(aright, bright)) / 2.0;
            }

            if (aleft > bright) {
                right = partitionA - 1;
            } else {
                left = partitionA + 1;
            }
        }

        throw new IllegalArgumentException("Invalid input");
    }
}
