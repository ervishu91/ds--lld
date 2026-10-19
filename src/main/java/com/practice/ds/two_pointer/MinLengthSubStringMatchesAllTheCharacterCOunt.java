package com.practice.ds.two_pointer;

import javax.net.ssl.ManagerFactoryParameters;
import java.util.HashMap;
import java.util.Map;

public class MinLengthSubStringMatchesAllTheCharacterCOunt {
    public static void main(String[] args) {
        System.out.println("max lngth is :" + minLengthSubStringMatchesAllTheCharacterCOunt("ADOBECODEBANC", "ABC"));
    }

    private static int minLengthSubStringMatchesAllTheCharacterCOunt(String s, String t) {

        int len = Integer.MAX_VALUE;

        Map<Character, Integer> tmap = new HashMap<>();
        Map<Character, Integer> smap = new HashMap<>();

        for (char ch : t.toCharArray()) {
            tmap.put(ch, tmap.getOrDefault(ch, 0) + 1);
        }

        int charReq = tmap.size();
        int left = 0;

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            smap.put(ch, smap.getOrDefault(ch, 0) + 1);

            if (tmap.containsKey(ch)
                    && tmap.get(ch).equals(smap.get(ch))) {
                charReq--;
            }

            while (charReq == 0) {

                len = Math.min(len, i - left + 1);

                char leftch = s.charAt(left);

                // FIX 1
                smap.put(leftch, smap.get(leftch) - 1);

                if (tmap.containsKey(leftch)
                        && smap.get(leftch) < tmap.get(leftch)) {
                    charReq++;
                }

                // FIX 2
                left++;
            }
        }

        return len == Integer.MAX_VALUE ? 0 : len;
    }
}
