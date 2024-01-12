package google.android_unlock_pattern;

import java.util.*;

class Pair{
    int first;
    int second;
    
    Pair(int first, int second){
        this.first = first;
        this.second = second;
    }
    
    private int key(){ return 10 * first + second; }
    
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        return this.key() == ((Pair)o).key();
    }
    
    @Override
    public int hashCode(){ return this.key(); }
}


public class Solution {
    
    Map<Pair, Integer> skip = new LinkedHashMap<>();
    
    {
        int[][] sarr = {
            // horizontal
            {1,3,2},
            {4,6,5},
            {7,9,8},
            //vertical
            {1,7,4},
            {2,8,5},
            {3,9,6},
            //diagonals
            {1,9,5},
            {3,7,5}
        };
        
        for(int[] arr: sarr){
            for(int i =0; i<arr.length; i++){
                skip.put(new Pair(arr[0], arr[1]), arr[2]);
                skip.put(new Pair(arr[1], arr[0]), arr[2]);
            }
        }
    }
   
    
    private int dfs(int currVal, int size, Set<Integer> seen, int low, int high){
        if(size == high) return 1;
        
        int res = size >= low && size < high ? 1 : 0;
         for(int i = 1; i<=9; i++){
             Pair move = new Pair(currVal, i);
             boolean isSeen = seen.contains(i);
             boolean isSkipMove = skip.containsKey(move);
             boolean isValidSkipMove = isSkipMove && seen.contains(skip.get(move));
             
             if(!isSeen && (!isSkipMove || isValidSkipMove)){
                 seen.add(i);
                 res += dfs(i, size+1, seen, low, high);
                 seen.remove(i);
             }
         }
        
        return res;
    }
    
    public int numberOfPatterns(int m, int n) {
       int res = 0;
       Set<Integer> seen = new LinkedHashSet<>();
       
        // corner 
          seen.clear();
          seen.add(1);
          res += dfs(1, 1, seen, m, n) * 4;
           
        // center
          seen.clear();
          seen.add(2);
          res += dfs(2, 1, seen, m, n) * 4;
           
        // center
          seen.clear();
          seen.add(5);
          res += dfs(5, 1, seen, m, n);
            
       
        return res;
    }
}