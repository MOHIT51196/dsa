package problems.first_missing_positive.without_memory;

public class Solution {
    public int firstMissingPositive(int[] nums) {
        // eliminating negatives
        for (int i = 0; i < nums.length; i++) nums[i] = Math.max(nums[i], 0);

        // marking visits
        for (int i = 0; i < nums.length; i++){
            int x = Math.abs(nums[i]);
            if(x <= nums.length && x >= 1) {
                if(nums[x - 1] > 0) nums[x - 1] *= -1;
                else if (nums[x - 1] == 0) nums[x - 1] = -1 * (nums.length + 1);
            }
        }


        for (int i = 0; i < nums.length; i++) if(nums[i] >= 0) return i + 1 ;
        return nums.length + 1;
    }
}
