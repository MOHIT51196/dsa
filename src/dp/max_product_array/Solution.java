package dp.max_product_array;

public class Solution {
    public int maxProduct(int[] nums) {
        int pMin = 1;
        int pMax = 1;
        int max = Integer.MIN_VALUE;
        
        for(int n : nums){
            
            int mn = pMin * n;
            int mx = pMax * n;
            pMin = Math.min(Math.min(mn, mx), n);
            pMax = Math.max(Math.max(mn, mx), n);
            
            max = Math.max(max, pMax);
        }
        return max;
        
    }
}