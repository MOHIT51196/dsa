package dp.median2array;

import java.util.Arrays;

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length = nums1.length + nums2.length;
        int[] res = new int[length];
        System.arraycopy(nums1, 0, res, 0, nums1.length);
        System.arraycopy(nums2, 0, res, nums1.length, nums2.length);

        Arrays.sort(res);
        int medIndex = length / 2;

        if(length % 2 == 0){
            return (res[medIndex] + res[medIndex -1]) /2f;
        } else return res[medIndex];
    }

    public static void main(String[] args) {
        Solution s= new Solution();
        double med = s.findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4});
        System.out.println("\nRESULT = " + med);
    }
}
