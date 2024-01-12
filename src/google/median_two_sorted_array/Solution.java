package google.median_two_sorted_array;

import java.util.Arrays;

class Solution {

    // ===================== SOLUTION 2 ==========================


    // search partition with binary search
    private double searchPartition(int[] nums1, int[] nums2, int total){
        int midX = nums1.length / 2;
        int midY = total/2 - midX;

        int left1 = midX == 0 ? Integer.MIN_VALUE : nums1[midX-1];
        int left2 = midY == 0 ? Integer.MIN_VALUE : nums2[midY-1];
        int right1 = midX == total - nums2.length ? Integer.MAX_VALUE : nums1[midX];
        int right2 = midY == nums2.length ? Integer.MAX_VALUE : nums2[midY];

        boolean con1 = right2 > left1;
        boolean con2 = right1 > left2;

        if (!con1 && con2) return searchPartition(Arrays.copyOfRange(nums1, 0, midX), nums2, total);
        if (con1 && !con2) return searchPartition(Arrays.copyOfRange(nums1, midX , nums1.length), nums2, total);

        if(total % 2 == 0 ) return Math.min(right1 , right2) + Math.max(left1 , left2) / 2f;
        else return Math.min(nums1[midX], nums2[midY]);

    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int total = nums1.length + nums2.length;

        // find shortest array
        return nums1.length < nums2.length ? searchPartition(nums1, nums2, total) : searchPartition(nums2, nums1, total);
    }
    
    // ===================== SOLUTION 1 ==========================
    
        // public double findMedianSortedArrays(int[] nums1, int[] nums2) {
//         int length = nums1.length + nums2.length;
//         int[] res = new int[length];
//         System.arraycopy(nums1, 0, res, 0, nums1.length);
//         System.arraycopy(nums2, 0, res, nums1.length, nums2.length);

//         Arrays.sort(res);
//         int medIndex = length / 2;

//         if(length % 2 == 0){
//             return (res[medIndex] + res[medIndex -1]) /2f;
//         } else return res[medIndex];
//     }
}