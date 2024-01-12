package google.longest_path_in_matrix;

import java.util.Arrays;

class Solution {
    
    
    private int dfs(int[][] matrix, int r, int c, int prevValue, int[][] cache){
        int m = matrix.length;
        int n = matrix[0].length;
        if(r >= m || r < 0 || c >= n || c < 0) return 0;        
        int val = matrix[r][c];
        if(val <= prevValue) return 0;
        
        if(cache[r][c] >= 0) return cache[r][c]; 
        
        int res = 1;
        res = Math.max(res, 1 + dfs(matrix, r - 1, c, val, cache));
        res = Math.max(res, 1 + dfs(matrix, r + 1, c, val, cache));
        res = Math.max(res, 1 + dfs(matrix, r, c - 1, val, cache));
        res = Math.max(res, 1 + dfs(matrix, r, c + 1, val, cache));
        cache[r][c] = res;

        return res;
    }
    
    public int longestIncreasingPath(int[][] matrix) {
        
        int[][] cache = new int[matrix.length][matrix[0].length];
        for(int[] arr : cache) Arrays.fill(arr, -1);
        
        int maxlen = 0;
        for(int i = 0; i< matrix.length; i++){
            for(int j = 0; j< matrix[i].length; j++){
                maxlen = Math.max(maxlen, dfs(matrix, i, j, -1, cache));
            }
        }
        return maxlen;

    }
}