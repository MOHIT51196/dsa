package dp.longest_palindrome;

import java.util.Scanner;

// O(n^3)
class Solution {

    boolean isPalindrome(String str){
        char[] chars = str.toCharArray();
        int midIndex = chars.length / 2;
        int i = 0;
        for (; i < midIndex && chars[i] == chars[chars.length - i -1 ]; i++);
        return i == midIndex;
    }

    public String longestPalindrome(String s) {
        String lpd = "";
        if(s.length() == 1) return s;
        for (int i =0; i < s.length(); i++){
            for (int j =i+1; j <= s.length(); j++) {
                String substr = s.substring(i, j);
//                System.out.println("SUBSTR = " + substr);
                lpd = isPalindrome(substr) && substr.length() > lpd.length() ? substr : lpd;
            }
        }
        return lpd.trim().length() == 0 ? null : lpd;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the string :");
        String line = in.nextLine();
        line.substring(1, 1);

        System.out.println("\nINPUT STRING : " +         line);
        String res = s.longestPalindrome(line);
        String ans = (( res == null) ? "N/A" : res);
        System.out.println("IS PALINDROME : " + ans);
    }
}