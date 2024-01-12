package dp.regex;

import java.util.Scanner;

public class Solution {
//    p   : ab*b
//    inp : abbn
    public boolean isMatch(String s, String p) {



        int i = 0, j =0;

        for (; i < s.length() && j < p.length();) {
            if(s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {
                j++; i++;
            }
            else {
                if(p.charAt(j) == '*') {
                    if (s.charAt(i) == p.charAt(j-1) || p.charAt(j-1) == '.') {
                        i++;
                    }
                    else {
                        j++;
                    }
//                } else return false;
                } else j++;
            }
        }

//        for(; j< p.length() && p.charAt(j) == '*'; j++) ;

//        return i == s.length() && j == p.length();
        return i == s.length() ;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the string : ");
        String inp = in.nextLine();

        System.out.println("Enter the pattern : ");
        String pattern = in.nextLine();

        System.out.println("Match Result = " + new Solution().isMatch(inp, pattern));
        in.close();

    }
}