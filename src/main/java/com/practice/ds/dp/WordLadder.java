package com.practice.ds.dp;

import java.util.*;

public class WordLadder {
    public static void main(String[] args) {
        System.out.println("word laddr is ::"+wordLadder("hit","cog",new String[]{"hot","dot","dog","lot","log","cog"}) );
    }

    private static int wordLadder(String start, String end,String[] words) {

        Set<String> wordSet = new HashSet<>(Arrays.asList(words));
        if(!wordSet.contains(end))
            return -1;
        if(start.equals(end))
            return 1;
        int len =1;
        Queue<String> que = new LinkedList<>();
        que.add(start);
        wordSet.remove(start);


        while(!que.isEmpty()) {
            int size = que.size();
            while (size-- > 0) {
                String curr = que.poll();
                System.out.println("curr:"+curr+",len::"+len);
                if (curr.equals(end))
                    return len;
                char[] charArray = curr.toCharArray();
                for (int i = 0; i < charArray.length; i++) {
                    char old = charArray[i];
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        if (ch == old)
                            continue;
                        charArray[i] = ch;
                        String newString = new String(charArray);
                        if(wordSet.contains(newString)) {
                            que.add(newString);
                            wordSet.remove(newString);
                        }

                    }
                    charArray[i] = old;
                }
            }
            len++;

        }
        return -1;
    }
}
