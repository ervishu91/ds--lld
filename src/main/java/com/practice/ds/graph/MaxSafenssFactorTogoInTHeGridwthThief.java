package com.practice.ds.graph;

import java.util.LinkedList;
import java.util.Queue;

public class MaxSafenssFactorTogoInTHeGridwthThief {

    public static void main(String... args){
        System.out.println("maxSafeneess facotr is ::"+maxSafenssFactorTogoInTHeGridwthThief(new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0},
                {0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0}
        }));
    }

    private static int maxSafenssFactorTogoInTHeGridwthThief(int[][] grid) {
        int safeFact =1;
        int maxSafe=0;
        int n=grid.length;
        int m = grid[0].length;

        getSafetyGrid(grid,n,m);
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                maxSafe = Math.max(maxSafe,grid[i][j]);
            }
        }
        while(safeFact<maxSafe){
            int mid = safeFact + (maxSafe-safeFact)/2;
            if(pathPossisble(grid,mid,n,m)){
                safeFact=mid;
            }
            else{
                maxSafe=mid-1;
            }
        }
        return safeFact;

    }

    private static boolean pathPossisble(int[][] grid, int mid,int n,int m) {
        int[][] clone = new int[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++)
                clone[i][j]=grid[i][j];
        }
        return dfs(clone,mid,0,0,n-1,m-1);

    }

    private static boolean dfs(int[][] grid, int mid ,int i, int j, int n, int m) {
        if(i==n && j==m)
            return true;
        int[][] neighbour = {{1,0},{-1,0},{0,1},{0,-1}};
        grid[i][j]=-1;
        for (int[] near : neighbour) {
            int x = i + near[0];
            int y = j + near[1];
            if (x >= 0 && x <=n && y >= 0 && y <=m && grid[x][y] >=mid) {

                if(dfs(grid,mid,x,y,n,m))
                    return true;
            }
        }
        return false;
    }


    public static void getSafetyGrid(int[][] grid,int n,int m) {
        Queue<int[]> que = new LinkedList<>();
        int[][] neighbour = {{1,0},{-1,0},{0,1},{0,-1}};
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==1) {
                    que.offer(new int[]{i, j});
                    grid[i][j]=0;
                }else{
                    grid[i][j]=-1;
                }
            }
        }
        while(!que.isEmpty()) {
            int[] cell = que.poll();
            for (int[] near : neighbour) {
                int x = cell[0] + near[0];
                int y = cell[1] + near[1];
                if (x >= 0 && x < n && y >= 0 && y < m && grid[x][y] == -1) {
                    grid[x][y] = grid[cell[0]][cell[1]] + 1;
                    que.offer(new int[]{x, y});
                }
            }
        }

    }
}
