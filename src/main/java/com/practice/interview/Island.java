package com.practice.interview;

public class Island {

    static int[][] near = {{0,1},{0,-1},{1,0},{-1,0}};
    public static void main(String[] args) {
        System.out.println("no of island::"+island(new char[][]{
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        }));
    }
    private static int island(char[][] grid){
        int island=0;
        int n = grid.length;
        if(n<=0)
            return island;
        int m = grid[0].length;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]=='1'){
                    island++;
                    findIsland(grid,i,j,n,m);
                }
            }
        }

        return island;
    }

    private static void findIsland(char[][] grid, int i, int j, int n, int m) {
        if(grid[i][j]!='1')
            return;
        grid[i][j]='2';

        for(int[] nr : near){
            int x=i+nr[0];
            int y = j+nr[1];
            if(x<0 || x>=n || y<0 || y>=m || grid[x][y]!='1')
                continue;
            findIsland(grid,x,y,n,m);
        }
    }
}
