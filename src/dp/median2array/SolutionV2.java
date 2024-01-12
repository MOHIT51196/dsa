package dp.median2array;

import java.util.Arrays;

public class SolutionV2 {

    // TIP : assume mid on right and mid-1 on left
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
        else return Math.min(nums1[midX], nums2[midY]); // right is mid for odd

    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int total = nums1.length + nums2.length;

        // find shortest array
        return nums1.length <= nums2.length ? searchPartition(nums1, nums2, total) : searchPartition(nums2, nums1, total);
    }

    public static void main(String[] args) {
        SolutionV2 s= new SolutionV2();
        double med = s.findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4});
        System.out.println("\nRESULT = " + med);
    }
}
