package com.practice.ds.graph;

public class Islandproblem {
    static int[] parent;
    static int island=0;
    public static void main(String[] args) {
        System.out.println("island required"+islandRequired(new char[][]{
                {'1','1','0','0','0','1','1'},
                {'1','1','0','0','0','0','0'},
                {'0','0','1','0','1','1','0'},
                {'0','0','0','0','1','0','0'},
                {'1','0','0','1','0','0','1'},
                {'1','0','0','1','1','0','1'}
        }));
    }

    private static int islandRequired(char[][] graph) {
        int[][] neighbour = {{1,0},{0,1}};
        int m = graph.length;
        int n = graph[0].length;
        island=0;
        parent = new int[m*n+1];
         if(m==0)
            return 0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(graph[i][j]=='1'){
                    island++;
                    parent[i*n+j] = i*n+j;
                }
            }
        }
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++) {
                if(graph[i][j]=='1'){
                    for(int[] neigh : neighbour){
                        int x = i+neigh[0];
                        int y = j+ neigh[1];
                        if(x<m && x>=0 && y<n && y>=0 && graph[x][y]=='1'){
                            union(i*n+j,x*n+y);
                        }
                    }
                }
            }
        }
        return island;
    }

    public static void union(int i,int y){
        int pi = find(i);
        int py = find(y);
        if(pi==py)
            return;
        if(pi<py){
            parent[py]=pi;
        }
        else {
            parent[pi] = py;
        }
        island--;

    }
    public static int find(int x){
        if(x!=parent[x])
            parent[x]= find(parent[x]);
        return parent[x];
    }
}
