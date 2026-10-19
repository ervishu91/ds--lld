package com.practice.interview;

import java.util.*;

public class FIndAllPaths {

    static List<List<String>> paths= new ArrayList<>();
    static Map<String, List<String>> graph;

    public static void main(String[] args) {
         graph = new HashMap<>();

        graph.put("A", List.of("B", "C"));
        graph.put("B", List.of("A", "C", "D"));
        graph.put("C", List.of("A", "B", "D"));
        graph.put("D", List.of("B", "C"));

        findAllpaths("A","D",new ArrayList<String>(),new HashSet<String>());
        for(List<String> pth : paths){
            System.out.println("");
            pth.forEach((k)-> System.out.print(k+"->"));
            System.out.println("D");
        }

    }

    private static void findAllpaths(String curr, String dest, List path, Set visit) {
        if(curr.equals(dest)){
            paths.add(new ArrayList<String>(path));
        }
        path.add(curr);
        visit.add(curr);

        for(String node :graph.getOrDefault(curr,Collections.emptyList())){
            if(!visit.contains(node)){
                findAllpaths(node,dest,path,visit);
            }
        }
        path.removeLast();
        visit.remove(curr);

    }


}
