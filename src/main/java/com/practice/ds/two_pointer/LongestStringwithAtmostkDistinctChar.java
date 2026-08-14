package com.practice.ds.two_pointer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestStringwithAtmostkDistinctChar {

    public static String getDistinctString(String str, int k){
        String response ="";
        int n = str.length();
        Map<Character,Integer> charMap = new HashMap<>();
        Set<Character> distictChar = new HashSet<>();
        int left=0;
        for(int i=0;i<n;i++){
            char ch = str.charAt(i);
            charMap.put(ch,charMap.getOrDefault(ch,0)+1);
            distictChar.add(ch);
            while(distictChar.size()>k && left<=i){
                char leftCh = str.charAt(left);
                int freq = charMap.put(leftCh,charMap.get(leftCh)-1);
                if(freq==1){
                    distictChar.remove(leftCh);
                }
                left++;

            }
            if(response.length()< i-left+1) {
                response = str.substring(left, i + 1);
            }
        }

        return response;

    }
    public static void main(String[] args){

        String str = "eceab";
        System.out.println(str+",KdistinctmaxLengthString:: "+getDistinctString("eceab",2));
        System.out.println(str+",KdistinctmaxLengthString:: "+getDistinctString("ccaabbb",2));
        System.out.println(str+",KdistinctmaxLengthString:: "+getDistinctString("abc",1));
        System.out.println(str+",KdistinctmaxLengthString:: "+getDistinctString("abc",0));

    }
}
