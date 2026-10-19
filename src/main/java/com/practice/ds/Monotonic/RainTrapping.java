package com.practice.ds.Monotonic;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class RainTrapping {
    public static void main(String[] args) {
        System.out.println("rain trapped is ::deque::"+rainTrappedIsWithDeque(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
        System.out.println("rain trapped is ::2pointer::"+rainTrappedIsWith2Pointer(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));


    }

    private static int rainTrappedIsWith2Pointer(int[] arr) {

        int rain = 0, n = arr.length;
        int leftmax = arr[0];
        int rightmx = arr[n - 1];
        int left = 0, right = n - 1;
        while (left <= right) {
            if (arr[left] <= arr[right]) {
                if (arr[left] >= leftmax)
                    leftmax = arr[left];
                else {
                    rain += leftmax - arr[left];
                }
                left++;
            } else {
                if (arr[right] >= rightmx)
                    rightmx = arr[right];
                else {
                    rain += rightmx - arr[right];
                }
                right--;
            }

        }
        return rain;
    }

    private static int rainTrappedIsWithDeque(int[] arr) {
        int rain=0;
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<arr.length;i++){
            while(!stack.isEmpty() && arr[i]>arr[stack.peek()]){
                int pitIndex = stack.pop();
                if(stack.isEmpty())
                    break;

                int height = Math.min(arr[i], arr[stack.peek()]);
                    int water = (height - arr[pitIndex])* (i - stack.peek() - 1);
                    rain += water;
                    //System.out.println("i:"+i+",left:"+stack.peek()+",water:"+water+",total:"+ rain);

            }
            stack.push(i);
            //System.out.println("i pushed:"+i+",left:"+stack.peek()+"total:"+ rain);


        }
        return rain;
    }

}
