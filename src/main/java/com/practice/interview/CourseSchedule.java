package com.practice.interview;

import java.util.*;

public class CourseSchedule {

    public static void main(String[] args) {
        System.out.println("course schedule possible::"+courseSchedule(2, new int[][] {{1, 0}, {0, 1}}));
    }

    private static boolean courseSchedule(int n, int[][] dep) {
        int[] indegree = new int[n];
        Map<Integer, Set<Integer>> courseDepMap = new HashMap<>();
        Queue<Integer> que = new LinkedList<>();
        List<Integer> res= new ArrayList<>();
        for(int[] coruseDep : dep){
            int a = coruseDep[0];
            int b = coruseDep[1];
            if(courseDepMap.computeIfAbsent(b,set-> new HashSet<>()).add(a)){
                indegree[a]++;
            }
        }
        for(int i=0;i<n;i++){
            if(indegree[i]==0) {
                que.offer(i);
            }
        }
        while(!que.isEmpty()){
            int curr = que.poll();
            for(int sub : courseDepMap.getOrDefault(curr, Collections.emptySet())){
                indegree[sub]--;
                if(indegree[sub]==0)
                    que.offer(sub);
            }
            res.add(curr);
        }

        return res.size()==n;

    }

}
