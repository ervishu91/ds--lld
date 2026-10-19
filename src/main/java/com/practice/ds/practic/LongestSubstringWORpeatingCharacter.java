package com.practice.ds.practic;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWORpeatingCharacter {
    public static void main(String[] args) {
        System.out.println("Stirng is ::"+longestSubstringWORpeatingCharacter("abcabcbb"));
    }

    private static String longestSubstringWORpeatingCharacter(String string) {
        String ans="";
        int len = Integer.MAX_VALUE;
        int left=0,n=string.length();
        Map<Character,Integer> charMap = new HashMap<>();
        for(int i=0;i<n;i++){
            char ch = string.charAt(i);
            if(charMap.containsKey(ch)){
                left = Math.max(left, charMap.get(ch)+1);
            }
            if(ans.isEmpty()|| ans.length()<i-left+1){
                ans = string.substring(left,i+1);
            }
            charMap.put(ch,i);

        }


        return ans;

    }
}
