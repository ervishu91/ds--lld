package com.practice.ds.practic;

public class WordSearch {

    public static int[][] neighbour= {{1,0},{-1,0},{0,1},{0,-1}};

    public static void main(String[] args) {
        System.out.println("wordSearch ::"+wordSearch(new char[][] {
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        },"ABCB"));
    }

    private static boolean wordSearch(char[][] grid, String s) {
        char[] charArray = s.toCharArray();
        int n = grid.length;
        int m = grid[0].length;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==charArray[0]){
                    if(dfs(grid,i,j,0,new boolean[n][m],n,m,charArray)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean dfs(char[][] grid, int i, int j, int wordIndex, boolean[][] visit, int n, int m,char[] wordArray) {
        if(wordIndex==wordArray.length)
            return true;
        if(grid[i][j]!=wordArray[wordIndex])
                return false;
        visit[i][j] = true;
        for(int[] near : neighbour){
            int x = i+near[0];
            int y = j+near[1];
            if(x<0 || x>=n || y<0 || y>=m || visit[x][y])
                continue;
            if(dfs(grid,x,y,wordIndex+1,visit,n,m,wordArray))
                return true;
        }

        visit[i][j] = false;

        return false;
    }
}
