package com.practice.ds.two_pointer;

import java.util.Deque;
import java.util.LinkedList;

public class LongestSubArrayWithMaxMinDifLimitK {

    static int longestSubArrayWithMaxMinDifLimitK(int[] list,int k){
        int length=1;
        int left=0;
        Deque<Integer> maxQue =  new LinkedList<>();
        Deque<Integer> minque =  new LinkedList<>();
        for(int i=0;i<list.length;i++){
            int curr = list[i];
            while(!maxQue.isEmpty() && list[maxQue.getLast()]<=curr){
                maxQue.removeLast();
            }
            maxQue.addLast(i);
            while(!minque.isEmpty() && list[minque.getLast()]>=curr){
                minque.removeLast();
            }
            minque.addLast(i);
            while(list[maxQue.getFirst()]-list[minque.getFirst()]>k){
                if(maxQue.getFirst()<=left)
                        maxQue.removeFirst();
                if(minque.getFirst()<=left)
                    minque.removeFirst();
                left++;
            }
            length = Math.max(length,i-left+1);
        }
        return length;
    }

    public static void main(String[] args) {
        System.out.println("max length is ::"+longestSubArrayWithMaxMinDifLimitK(new int[]{10, 1, 2, 4, 7, 2},5));
    }
}
