package dp.max_sum_array;

public class Solution {
    public int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int sum = 0;
        
        for(int n : nums){
            if(sum < 0) sum = 0; 
            sum += n;
            // sum = Math.max(n, sum + n);
            maxSum = Math.max(sum, maxSum);
        }
        return maxSum;
    }
};