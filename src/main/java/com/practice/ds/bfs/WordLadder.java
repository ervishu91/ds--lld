package com.practice.ds.bfs;


import java.util.*;

public class WordLadder {

    public static void main(String[] args) {
        System.out.println("min dist is ::"+wordLadderMin("hit","cog", new String[]{"hot", "dot", "dog", "lot", "log", "cog"}));
    }

    private static int wordLadderMin(String start, String end, String[] words) {
        Set<String> dict = new HashSet<>(List.of(words));
        if(!dict.contains(end))
            return 0;
        if(start.equals(end))
            return 1;
        Queue<String> que = new LinkedList<>();
        int dist=1;
        que.offer(start);
        dict.remove(start);
        while(!que.isEmpty()){
            int size = que.size();
            while(size-->0){
                String curr = que.poll();
                if(curr.equals(end))
                    return dist;
                char[] currArray = curr.toCharArray();
                for(int i=0;i<currArray.length;i++){
                    char old = currArray[i];
                    for(char ch ='a';ch<='z';ch++){
                        if(ch!=old) {
                            currArray[i] = ch;
                            String newWord = new String(currArray);
                            if(dict.contains(newWord)){
                                que.offer(newWord);
                                dict.remove(newWord);
                            }
                        }
                    }
                    currArray[i] = old;
                }

            }
            dist+=1;
        }
        return -1;
    }
}
