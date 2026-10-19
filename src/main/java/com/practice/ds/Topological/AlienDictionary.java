package com.practice.ds.Topological;

import java.util.*;

public class AlienDictionary {

    public static void main(String[] args) {
        System.out.println("Dictionary order is "+alienDictionary(new String[]{ "wrt",
                "wrf",
                "er",
                "ett",
                "rftt"}));
    }

    private static String alienDictionary(String[] words) {
        String order="";
        int n = words.length;
        //1 creta indgree for eevry char
        //2 create depordring or invalid case graph for each char
        //3.go oer topological sort and find order

        if(words.length<1)
            return "";
        if(words.length==1)
            return words[0];
        Map<Character,Integer> inDegreeMap = new HashMap<>();
        Map<Character, Set<Character>> depMap = new HashMap<>();
        for(String word : words){
            char[] charArray = word.toCharArray();
            for(char ch : charArray){
                inDegreeMap.putIfAbsent(ch,0);
                depMap.putIfAbsent(ch,new HashSet<>());
            }
        }
        String prev=words[0];
        for(int i=1;i<n;i++){
            String next = words[i];
            int j=0;
            boolean diff=false;
            while(j<next.length() && j <prev.length()){
                if(prev.charAt(j)!=next.charAt(j)){
                    diff=true;
                    break;
                }
                j++;
            }
            if(!diff && prev.length() > next.length())
                return "";// invalid case, if next is orefix of prev, then it is invalid

            if(diff){
                char prevch = prev.charAt(j);
                char nextch = next.charAt(j);
                if(depMap.get(prevch).add(nextch))
                    inDegreeMap.put(nextch,inDegreeMap.get(nextch)+1);

            }
            prev=next;
        }
        Queue<Character> que = new LinkedList<>();
        for(char ch : inDegreeMap.keySet()){
            if(inDegreeMap.get(ch)==0)
                    que.add(ch);
        }
        StringBuilder sb = new StringBuilder();
        while(!que.isEmpty()){
            char ch = que.poll();
            sb.append(ch);
            for(char next : depMap.get(ch) ){
                int inDegree = inDegreeMap.get(next);
                inDegreeMap.put(next,inDegree-1);
                if(inDegree==1){
                    que.add(next);
                }
            }
        }
        if (sb.length() != inDegreeMap.size()) {
            return "";
        }
        return sb.toString();
    }
}
