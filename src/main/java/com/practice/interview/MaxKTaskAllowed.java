package com.practice.interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static java.util.concurrent.ForkJoinPool.commonPool;

public class MaxKTaskAllowed {

    static int process(int k){
        return k*k;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int k = 2;
        int[] nums = {1, 2, 3, 5};
        List<Future<Integer>> result = new ArrayList<>();
        ExecutorService exe = Executors.newFixedThreadPool(k);

        try {
            for (int num : nums) {
                result.add(exe.submit(() ->
                        process(num)));
            }

            Thread.sleep(2000);
            for (int i = 0; i < nums.length; i++) {
                System.out.println(result.get(i).get());
            }
        } finally {
            exe.shutdown();
        }
    }
}
