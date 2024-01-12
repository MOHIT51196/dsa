package problems.split_array_largest_sum;

public class Solution {
    
    private boolean shouldSplit(int[] nums, int m, int threshold){
        // greedy approach
        int sum = 0;
        int possibleSubArrays = 0;
        for(int n : nums){
            sum += n;
            if(sum > threshold){
                sum = n;
                possibleSubArrays++;
            }
        }
        return possibleSubArrays + 1 <= m;
    }
    
   
    
    public int splitArray(int[] nums, int m) {
        // binary search in order to minimise the sum
        // condition in binary search will be an iteration
        int l = -1; // lowest possible largest sum subarray with size 1
        int r = 0; // total sum of array as max possible value hypothetically
        for(int n : nums) {
            l = Math.max(l, n);
            r += n;
        }
        
        
        int res = r;
        while(l <= r){
            int mid = (l + r) / 2;
            if(shouldSplit(nums, m, mid)) {
                res = mid;
                r = mid - 1;
            }
            else l = mid + 1;
        }
        return res;
    }
}