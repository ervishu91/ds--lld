package com.practice.ds.BinarySearch;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class FindMaxSafestScoreRootInAGrid {


    public static int[][] neigh={{0,1},{0,-1},{1,0},{-1,0}};
        public static void main(String[] args) {
        System.out.println("maxScore for the root is ::"+findMaxSafestScoreRootInAGrid(new int[][]{
                {0,0,1},
                {0,0,0},
                {0,0,0}
        }));
    }

    private static int findMaxSafestScoreRootInAGrid(int[][] grid) {
        calculateMinSafeDistance(grid);
        Arrays.stream(grid).forEach(a-> System.out.println(Arrays.toString(a)));
        int n = grid.length;
        int m = grid[0].length;
        int min=Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for(int i =0;i<n;i++){
            for(int j=0;j<m;j++) {
                if(grid[i][j]!=0){
                    min = Math.min(min,grid[i][j]);
                    max = Math.max(max,grid[i][j]);
                }
            }
        }
        while(min<max){
            int mid = min + (max-min)/2;
            if(pathPossible(grid,mid)){
                min=mid;
            }else{
                max=mid-1;
            }
        }


        return min;
    }

    private static boolean pathPossible(int[][] grid, int saf) {
        int n = grid.length;
        int m = grid[0].length;
        if(grid[0][0]<saf)
            return false;
        return dfs(grid,0,0,n,m,saf,new boolean[n][m]);
    }

    private static boolean dfs(int[][] grid,int i, int j, int n,int m, int safe, boolean[][] visit){
            if(i==n-1 && j==m-1)
                return true;
            visit[i][j]= true;
            for(int[] near : neigh){
                int nx = i + near[0];
                int ny = j + near[1];
                if (nx < 0 || ny < 0 || nx >= n || ny >= m || visit[nx][ny] || grid[nx][ny] <safe)
                    continue;
                if(dfs(grid,nx,ny,n,m,safe,visit))
                    return true;

            }
        visit[i][j]= false;
         return false;
    }

    private static void calculateMinSafeDistance(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        Queue<int[]> que = new LinkedList<>();
        for(int i =0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==1){
                    que.add(new int[]{i,j});
                    grid[i][j]=0;

                }else{
                    grid[i][j]=-1;
                }
            }
        }
        while(!que.isEmpty()){
            int size = que.size();

            while(size-->0) {
                int[] curr = que.poll();
                int x = curr[0];
                int y = curr[1];
                int currSaf = grid[x][y];
                for (int[] near : neigh) {
                    int nx = x + near[0];
                    int ny = y + near[1];
                    if (nx < 0 || ny < 0 || nx >= n || ny >= m || grid[nx][ny] != -1)
                        continue;

                    grid[nx][ny] = currSaf + 1;
                    que.offer(new int[]{nx, ny});

                }
            }

        }
    }
}
