package problems.three_sum.two_pointer;

import java.util.*;

public class Solution {

    private void twoSum(int[] nums, int i, int target, List<List<Integer>> res){
        int l = i + 1;
        int r = nums.length - 1;
        while(l < r){
            int left = nums[l];
            int right = nums[r];
            int sum = left + right;
            if(sum < target) l++;
            else if(sum > target) r--;
            else {
                 res.add(List.of(nums[i] , left, right));
                 l++;
                 r--;
                 while(l < r && nums[l] == nums[l - 1]) l++;
                 while(l < r && nums[r] == nums[r + 1]) r--;
            }
        }

    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if(i == 0 || nums[i] != nums[i-1]) {
               twoSum(nums, i, -nums[i], res);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{-1, 0, 0, 1, 1};
//        int[] nums = new int[]{0, 0, 0, 0};
//        int[] nums = new int[]{-1,0,1,0, 2,-1,0, -4};
//        int[] nums = new int[]{-1,0,1, 2,-1, -4};
        System.out.println("OUTPUT = " + s.threeSum(nums));
    }
}