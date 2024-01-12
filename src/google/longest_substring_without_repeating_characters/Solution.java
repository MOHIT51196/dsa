package google.longest_substring_without_repeating_characters;

import java.util.LinkedHashMap;
import java.util.Map;

public class Solution {
    // A B C D B
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> tracked = new LinkedHashMap<>();
        int l = 0;
        int max = 0;
        int i =0;
        while(i<s.length()){
            char c = s.charAt(i);
            if(tracked.containsKey(c) && tracked.get(c) >= l) {
                l = tracked.get(c) + 1;
            }
            tracked.put(c, i++);
            max = Math.max(max, i - l);
        }
        
        return max;
    }
}