package google.word_search.two;

import java.util.*;

// Throws TLE
public class Solution {
    char[][] mBoard;
    boolean[][] seen;
    
    Map<String, Boolean> dp = new LinkedHashMap<>();
    
    boolean dfs(int r, int c, int len, char[] words){
        int m = mBoard.length;
        int n = mBoard[0].length;
        if(len == words.length) return true;



        if(r < 0 || c < 0 || c >= n || r >= m || words[len] != mBoard[r][c] || seen[r][c]) return false;
        
        seen[r][c] = true;
        boolean res = dfs(r-1, c, len + 1, words)
            || dfs(r+1, c, len + 1, words)
            || dfs(r, c-1, len + 1, words)
            || dfs(r, c+1, len + 1, words);
        seen[r][c] = false;
        
        
        return res;
    }
    
    public List<String> findWords(char[][] board, String[] words) {
        this.mBoard = board;
        seen = new boolean[board.length][board[0].length];
        
        Set<String> ans = new HashSet<>();
        for(String word: words){
            for(int i =0; i<board.length; i++){
                for(int j = 0; j<board[0].length; j++){
                    if(dfs(i, j, 0, word.toCharArray())){
                        ans.add(word);
                    }
                }
            }
        }
        return new ArrayList<String>(ans);
    }
}