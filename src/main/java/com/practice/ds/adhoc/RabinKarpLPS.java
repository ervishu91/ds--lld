package com.practice.ds.adhoc;

import java.util.ArrayList;
import java.util.List;

public class RabinKarpLPS {

    public static void main(String[] args) {
        System.out.println("match is at :"+kmp("abccd","bcc"));
        //rabinkarp("abccd","bcc");
    }

    private static void rabinkarp(String text, String pat) {
        int d=26,prime=101,n=text.length(),m=pat.length();

        int h=1,pathash=0,texthash=0;
        if(text.isEmpty() || n<m)
            return;
        for(int j=1;j<m;j++){
            h=(h*d)%prime;
        }
        for(int i=0;i<m;i++){
            pathash = ((pathash*d)+pat.charAt(i))%prime;
            texthash = ((texthash*d)+text.charAt(i))%prime;
        }
        for(int i=0;i<=(n-m);i++){
            if(pathash==texthash){
                boolean match =true;
                for(int j=0;j<m;j++){
                    if(pat.charAt(j)!=text.charAt(i+j)){
                        match=false;
                        break;
                    }
                }
                if(match){
                    System.out.println("match at index::"+i);
                }
            }
            if(i<n-m){
                texthash = ((texthash-(h*text.charAt(i)))*d+text.charAt(i+m))%prime;
                if(texthash<0)
                    texthash+=prime;

            }

        }

    }


    public static int kmp(String text, String pattern) {

        int i = 0, j = 0;
        int n = text.length();
        int m = pattern.length();

        int[] lps = buildLPS(pattern);

        while (i < n) {

            if (text.charAt(i) == pattern.charAt(j)) {

                i++;
                j++;

                if (j == m) {
                    System.out.println("index: " + (i - j));

                    // If you want only first occurrence:
                    return i - j;

                    // For all occurrences, instead do:
                    // j = lps[j - 1];
                }

            } else {

                if (j > 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }

        return -1;
    }

    private static int[] buildLPS(String pattern) {

        int n = pattern.length();
        int[] lps = new int[n];

        int len = 0;
        int i = 1;

        while (i < n) {

            if (pattern.charAt(i) == pattern.charAt(len)) {

                len++;
                lps[i] = len;
                i++;

            } else {

                if (len > 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }
}
