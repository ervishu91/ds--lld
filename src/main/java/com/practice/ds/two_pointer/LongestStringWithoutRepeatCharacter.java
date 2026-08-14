package com.practice.ds.two_pointer;

import java.util.HashMap;
import java.util.Map;

public class LongestStringWithoutRepeatCharacter {

    static String longestStringWithoutRepeatCharacter(String str){
        String resp ="";
        int left=0;
        Map<Character,Integer> charMap = new HashMap<>();
        if(str.length()<=1)
            return str;

        for(int i=0;i<str.length();i++){
            char ch = str.charAt(i);
            if(charMap.containsKey(ch)){
                left = Math.max(left,charMap.get(ch)+1);
            }
            if(resp.length()<=(i-left+1))
                resp = str.substring(left,i+1);
            charMap.put(ch,i);

        }
        return resp;

    }

    public static void main(String[] args) {
        System.out.println("LongestStringWithoutRepeatCharacter::"+longestStringWithoutRepeatCharacter("tmmzuxt"));
    }
}
