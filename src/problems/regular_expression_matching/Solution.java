package problems.regular_expression_matching;

import java.util.Arrays;
import java.util.HashSet;
import java.util.TreeSet;

public class Solution {

    private boolean match(char a, char b){ return a == b || b == '.'; }

    private boolean dfs(String s, String p, int i, int j){
        if(i >= s.length() && j >= p.length()) return true;

        if(j < p.length()){
            boolean isMatched = i < s.length() && match(s.charAt(i), p.charAt(j));
            if((j+1) < p.length() && p.charAt(j+1) == '*'){
                return dfs(s, p, i, j+2) || (isMatched && dfs(s, p, i+1, j));
            }

            if(isMatched) return dfs(s, p, i+1, j+1);
        }

//        System.out.println("At characters = " + s.charAt(i) + " , " + p.charAt(j));
        return false;

    }

    public boolean isMatch(String s, String p) {
        return dfs(s, p, 0, 0);

    }

    public static void main(String[] args) {
        String text = "aa";
        String pattern = "a*";
        System.out.println("Matched( " + text + " , " + pattern + " ) = " + new Solution().isMatch(text, pattern) );
    }

}