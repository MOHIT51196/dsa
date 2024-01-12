// Input: s = "ADOBECODEBANC", t = "ABC"
// Output: "BANC"

import java.util.LinkedHashMap;
import java.util.Map;

public class Solution {
    public String minWindow(String s, String t) {
        char[] sarr = s.toCharArray();
        char[] tarr = t.toCharArray();
        Map<Character, Integer> tMap = new LinkedHashMap<>();
        Map<Character, Integer> sMap = new LinkedHashMap<>();
        int minLen = Integer.MAX_VALUE;
        int start = 0;
        int minStartIndex = 0;

        for(char c : tarr) tMap.put(c, 1 + tMap.getOrDefault(c, 0));
        int need = tMap.size();
        int have = 0;


        for(int i =0; i< sarr.length; i++){
            char c = sarr[i];
            sMap.put(c, 1 + sMap.getOrDefault(c, 0));

            if(tMap.containsKey(c) && sMap.get(c) == tMap.get(c)) {
                have++;
            }
            // System.out.println("at i = " + i + " value = " + c + " have = " + have);



            while(have == need) {
                int currLen = i - start + 1;
                if(currLen < minLen) {
                    // System.out.println("New min len = " + currLen + " and start = " + start);
                    minLen = currLen;
                    minStartIndex = start;
                }


                char cStart = sarr[start];
                sMap.put(cStart, sMap.get(cStart) - 1);


                if(tMap.containsKey(cStart) && sMap.get(cStart) < tMap.get(cStart)) {
                    have--;
                }
                start++;
            }
        }
        return (minLen == Integer.MAX_VALUE) ? "" : s.substring(minStartIndex, minStartIndex + minLen);
    }
}