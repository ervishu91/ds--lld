package com.practice.interview;

import java.util.*;

public class COnnectedComponent {


    static int[] parent;

    public static void main(String[] args) {
        Map<Integer, List<Integer>> graph = new HashMap<>();

        graph.put(0, Arrays.asList(1));
        graph.put(1, Arrays.asList(0,2));
        graph.put(2, Arrays.asList(1));
        graph.put(3, Arrays.asList(4));
        graph.put(4, Arrays.asList(3));
        graph.put(5, Collections.emptyList());
        parent = new int[graph.size()];
        for(int i=0;i<graph.size();i++){
            parent[i]= i;
        }
        for(int from : graph.keySet()){
            List<Integer> edge = graph.get(from);
            for(int to : edge){
                union(from,to);
            }
        }
        Map<Integer, List<Integer>> compmap = new HashMap<>();
        for(int i=0;i<graph.size();i++){
            compmap.computeIfAbsent(find(parent[i]),k-> new ArrayList<>()).add(i);
        }
        compmap.entrySet().forEach(k-> System.out.println(Arrays.toString(k.getValue().toArray())));
        //List<List<Integer>> comps = findCOnnectedCompoenent(graph);
        //comps.forEach(k-> System.out.println(Arrays.toString(k.toArray())));
    }
    static void union(int from, int to){
        int pfrom = find(from);
        int pto = find(to);
        if(pfrom==pto){
            return;
        }
        if(pfrom<pto){
            parent[pfrom]=pto;
        }else{
            parent[pto]=pfrom;
        }
    }
    static int find(int to){
        if(parent[to]!=to){
            parent[to]= find(parent[to]);
        }
        return parent[to];
    }

    private static List<List<Integer>> findCOnnectedCompoenent(Map<Integer, List<Integer>> graph) {
        List<List<Integer>> comp = new ArrayList<>();
        Set<Integer> visited= new HashSet<>();
        for(int vertex : graph.keySet()){
            if(!visited.contains(vertex)){
                List<Integer> component = new ArrayList<>();
                findComp(graph,vertex,visited,component);
                comp.add(component);
            }
        }

return comp;
    }

    private static void findComp(Map<Integer, List<Integer>> graph, Integer vertex, Set<Integer> visited, List<Integer> component) {
        visited.add(vertex);
        component.add(vertex);
        for(int to : graph.get(vertex)){
            if(!visited.contains(to)){
                findComp(graph,to,visited, component);
            }
        }

    }
}
