package com.practice.ds.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MeetingRoom2 {


    public static void main(String[] args) {
        System.out.println("room required::"+getminRoomRequired(new int[][]{{0,30},{5,10},{15,20},{25,35},{28,40},{12,18},{40,50},{45,55},{50,60},{10,15},{5,8},{18,25}}));
    }

    private static int getminRoomRequired(int[][] meetings) {
        int room=1;
        Arrays.sort(meetings,(a,b)-> Integer.compare(a[0],b[0]));
        PriorityQueue<Integer> minheap = new PriorityQueue<>();
            for(int[] meeting : meetings){
                while(!minheap.isEmpty() && minheap.peek() <=meeting[0]){
                    minheap.poll();
                }
                minheap.offer(meeting[1]);
                room = Math.max(room,minheap.size());
            }

        return room;
    }
}
