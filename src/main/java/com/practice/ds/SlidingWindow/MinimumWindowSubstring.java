package com.practice.ds.SlidingWindow;


import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {
    public static void main(String[] args) {
        System.out.println("minStirng is ::"+minimumWindowSubstring("ADOBECODEBANC","ABC"));
    }

    private static String minimumWindowSubstring(String s, String t) {
        String min ="";
        Map<Character,Integer> tmap = new HashMap<>();
        Map<Character,Integer> smap = new HashMap<>();
        for(char ch : t.toCharArray()){
            tmap.put(ch, tmap.getOrDefault(ch,0)+1);
        }
        int requiredChar = tmap.size();
        int left=0;
        for(int i=0;i<s.length();i++){

            char ch =s.charAt(i);
            smap.put(ch, smap.getOrDefault(ch,0)+1);
            if(tmap.containsKey(ch) && tmap.get(ch)==smap.get(ch))
                requiredChar--;
            while(requiredChar==0){
                if(min.isEmpty() || min.length() > i-left+1){
                    min = s.substring(left,i+1);
                    System.out.println("i:"+i+",left:"+left+",min::"+min+",requiredChar"+requiredChar);

                }
                char leftCh = s.charAt(left);
                int oldIndex= smap.get(leftCh);
                smap.put(leftCh, smap.get(leftCh)-1);
                if(tmap.containsKey(leftCh) && tmap.get(leftCh)!=smap.get(leftCh))
                    requiredChar++;
                if(oldIndex<=1)
                    smap.remove(leftCh);
                left++;
            }
        }
        return min;
    }
}
