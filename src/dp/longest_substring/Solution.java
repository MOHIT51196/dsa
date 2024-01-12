package dp.longest_substring;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution {

    public int lengthOfLongestSubstring(String s) {
        int maxCount = 0, count = 0;
        char[] sarr = s.toCharArray();
        System.out.println(s.length());
        Map<Character, Integer> store = new HashMap<>();
        for (int i =0; i< sarr.length; ) {
            char c = sarr[i];
            if (store.containsKey(c)) {
                i = store.get(c);
                store.clear();
                count = 0;
            } else {
                i++;
                count++;
                store.put(c, i);
                maxCount = Math.max(maxCount, count);
            }
        }

        return maxCount;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the string : ");
        String input = in.nextLine();

        int length = new Solution().lengthOfLongestSubstring(input);
        System.out.println("RESULT =" + length);
    }
}
