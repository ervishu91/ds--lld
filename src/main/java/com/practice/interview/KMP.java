package com.practice.interview;

public class KMP {
    static int[] lps;
    public static void main(String[] args) {
        String text = "ABAABCAB";
        String pat ="ABC";
        lps = new int[pat.length()];
        System.out.println("pat statr at ::"+findMatch(text,pat));
    }

    static int findMatch(String text, String pat){
        makeLPS(pat);
        return findFirsOccurence(text,pat);
    }

    private static int findFirsOccurence(String text, String pat) {
        int i=0,j=0;
        while(i< text.length() && j<pat.length()){
            if(text.charAt(i)==pat.charAt(j)) {
                i++;
                j++;
                if (j >= pat.length()) {
                    return i - j;
                }
            }else{
                    if(j>0){
                        j=lps[j-1];
                    }else{
                        i++;
                        j=0;
                    }
                }

        }
        return -1;
    }

    private static void makeLPS(String pat) {
        int len=0,i=1;
        while(i<pat.length()){
            if(pat.charAt(i)==pat.charAt(len)){
                len++;
                lps[i]=len;
                i++;
            }else{
                if(len>0){
                    len=lps[len-1];
                }else{
                    lps[i]=0;
                    i++;
                }
            }
        }
    }
}
