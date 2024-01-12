package google.odd_even_jump;

import java.util.Map;
import java.util.TreeMap;

public class Solution {
    // [2,3,1,1,4]
    public int oddEvenJumps(int[] arr) {
        int size = arr.length;
        boolean[] lower = new boolean[size]; // even
        boolean[] higher = new boolean[size]; // odd
        
        TreeMap<Integer, Integer> map = new TreeMap<>();
        
        map.put(arr[size - 1], size - 1);
        lower[size - 1] = true;
        higher[size - 1] = true;
        
        int count = 1;
        for(int i = size - 2; i >= 0; i--){
            Map.Entry<Integer, Integer> floor = map.floorEntry(arr[i]);
            Map.Entry<Integer, Integer> ceil = map.ceilingEntry(arr[i]);
            
            if(floor != null) lower[i] = higher[floor.getValue()];
            if(ceil != null) higher[i] = lower[ceil.getValue()];
            
            if(higher[i]) count++;
            map.put(arr[i], i);

        }
        return count;
    }
}