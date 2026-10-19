package com.practice.ds.adhoc;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {
    public static void main(String[] args) {
        System.out.println("mins String is "+ minimumWindowSubstring("ADOBECODEBANC","ABC"));
    }

    private static String minimumWindowSubstring(String s, String t) {

        if (s.isEmpty() || t.isEmpty() || t.length() > s.length()) {
            return "";
        }

        Map<Character, Integer> tmap = new HashMap<>();
        Map<Character, Integer> smap = new HashMap<>();

        for (char ch : t.toCharArray()) {
            tmap.put(ch, tmap.getOrDefault(ch, 0) + 1);
        }

        int required = tmap.size();

        int left = 0;
        String minMatch = "";

        for (int right = 0; right < s.length(); right++) {

            char ch = s.charAt(right);

            smap.put(ch, smap.getOrDefault(ch, 0) + 1);

            if (tmap.containsKey(ch)
                    && tmap.get(ch).equals(smap.get(ch))) {
                required--;
            }

            while (required == 0) {

                if (minMatch.isEmpty()
                        || right - left + 1 < minMatch.length()) {

                    minMatch = s.substring(left, right + 1);
                }

                char leftch = s.charAt(left++);

                smap.put(leftch, smap.get(leftch) - 1);

                if (tmap.containsKey(leftch)
                        && smap.get(leftch) < tmap.get(leftch)) {

                    required++;
                }
            }
        }

        return minMatch;
    }
}
