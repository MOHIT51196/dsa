package google.fruits_into_baskets;

import java.util.LinkedHashMap;
import java.util.Map;

public class Solution {
    public int totalFruit(int[] fruits) {
        int max = -1;

        Map<Integer, Integer> track = new LinkedHashMap<>();

        int l = 0;
        int r = 0;
        while(r < fruits.length){
            int fruit = fruits[r];
            track.put(fruit, r);

            if(track.size() > 2){
                Map.Entry<Integer, Integer> minEntry = null;
                for(Map.Entry<Integer, Integer> e: track.entrySet()){
                    if(minEntry == null || minEntry.getValue() > e.getValue()) minEntry = e;
                }


                if(minEntry != null) {
                    track.remove(minEntry.getKey());
                    l = minEntry.getValue() + 1;
                }

            }
            max = Math.max(max, r - l + 1);

            r++;

        }

        return max;
    }
}