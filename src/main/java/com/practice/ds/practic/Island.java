package com.practice.ds.practic;

public class Island {
    static int[] parent ;
    static int comp=0;
    public static void main(String[] args) {
        System.out.println("no of island is ::"+island(new int[][]{{1,1,0,0},
                {1,0,0,1},
                {0,0,1,1},
                {0,0,0,0}}));
    }

    private static int island(int[][] grid) {
        int n =grid.length;
        comp=0;
        if(n<=0)
                return 0;
        int m = grid[0].length;
        parent = new int[n*m];
        int[][] neighbour = {{1,0},{-1,0},{0,1},{0,-1}};

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==1){
                    parent[i*m+j]=i*m+j;
                    comp++;
                }

            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==1){
                   for(int[] near : neighbour){
                       int x = i+near[0];
                       int y = j+near[1];
                       if(x<0 || x>=n || y<0 ||y>=m || grid[x][y]==0)
                           continue;
                       union(i,j,x,y,n,m);
                   }
                }

            }
        }
        return comp;
    }
    private static void union(int i,int j,int x,int y,int row,int col){
        int px = find(i*col+j);
        int py = find(x*col+y);

        if(px==py)
            return;
        comp--;
        parent[py]= px;
    }
    private static int find(int x){
        if(parent[x]!=x){
            parent[x]= find(parent[x]);
        }
        return parent[x];
    }
}
