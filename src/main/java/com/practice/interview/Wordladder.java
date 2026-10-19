package com.practice.interview;

import java.util.*;

public class Wordladder {

    public static void main(String[] args) {
        //System.out.println("min steps are ::"+wordladder("hit","cog", new String[]{"hot","dot","dog","lot","log","cog"}));
        System.out.println("min steps are ::"+wordladderWith2WayBFS("hit","cog", new String[]{"hot","dot","dog","lot","log","cog"}));


    }

    private static int wordladderWith2WayBFS(String start, String end, String[] words) {
        int step=0;
        return step;
    }

    private static int wordladder(String start, String end, String[] words) {
        int step=1;

        Set<String> dict = new HashSet<>(List.of(words));
        if(!dict.contains(end))
            return -1;

        Queue<String> que = new LinkedList<>();
        que.add(start);
        que.remove(start);
        while(!que.isEmpty()){
            int n = que.size();
            while(n-->0){
                String curr= que.poll();
                if(curr.equals(end))
                    return step;
                char[] currArray = curr.toCharArray();
                for(int k=0;k<curr.length();k++){
                    char old= currArray[k];
                    for(char ch ='a';ch<='z';ch++){
                        if(ch!=old){
                            currArray[k]=ch;
                            String newWord = new String(currArray);
                            if(dict.contains(newWord)){
                                que.add(newWord);
                                dict.remove(newWord);
                            }

                        }

                    }
                    currArray[k]=old;
                }
            }
            step++;
        }

        return -1;
    }
}
