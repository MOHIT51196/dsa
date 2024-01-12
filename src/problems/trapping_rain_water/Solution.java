package problems.trapping_rain_water;

public class Solution {
    public int trap(int[] height) {
        
        int trapped = 0;
        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];
        
        leftMax[0] = height[0];
        for(int i = 1; i < height.length; i++) 
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        
        rightMax[height.length - 1] = height[ height.length - 1];
        for(int i = height.length - 2; i >= 0; i--) 
            rightMax[i] =  Math.max(rightMax[i + 1], height[i]);
        
        
        for(int i = 0; i< height.length; i++)
            trapped += Math.min(leftMax[i], rightMax[i]) - height[i];
        
        return trapped;
    }

    // BETTER SOLUTION : with O(1) space complexity
    public int trapV2(int[] height) {
        int l = 0;
        int r = height.length - 1;
        int leftMax = height[l];
        int rightMax = height[r];
        int sum = 0;

        while(l < r){
            if(leftMax <= rightMax){
                l++;
                leftMax = Math.max(leftMax, height[l]);
                sum += leftMax - height[l];
            } else {
                r--;
                rightMax = Math.max(rightMax, height[r]);
                sum += rightMax - height[r];

            }
        }
        return sum;
    }
}