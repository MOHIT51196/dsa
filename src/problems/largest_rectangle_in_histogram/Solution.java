package problems.largest_rectangle_in_histogram;

import java.util.Stack;

/*
Input: heights = [2,1,5,6,2,3]
Output: 10
 */

class StackEntry {
    int index;
    int height;

    public StackEntry(int index, int height) {
        this.index = index;
        this.height = height;
    }
}

public class Solution {
    public int largestRectangleArea(int[] heights) {
        final Stack<StackEntry> stack = new Stack<>();
        int maxArea = 0;

        for (int i = 0; i < heights.length; i++) {
            int currentHeight = heights[i];

            if(i == 0 || heights[i - 1] < currentHeight) stack.add(new StackEntry(i, currentHeight));
            else if( heights[i - 1] > currentHeight) {
                // start poping greator heights from the stack
                int lastPoopeedIndex = -1;
                while(!stack.isEmpty()){
                    StackEntry entry = stack.peek();
                    if(entry.height > currentHeight) {
                        maxArea = Math.max(entry.height * (i - entry.index), maxArea);
                        lastPoopeedIndex = stack.pop().index;
                    }
                    else break;
                }

                stack.add(new StackEntry(lastPoopeedIndex, currentHeight));

            }
            System.out.println();
            stack.forEach(e -> System.out.println(e.index + " , " + e.height));

        }

        while (!stack.isEmpty()){
            StackEntry e = stack.peek();
            maxArea = Math.max(e.height * (heights.length - e.index), maxArea);
            stack.pop();
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int area = new Solution().largestRectangleArea(new int[]{2,1,2});
        System.out.println("Area = " + area);
    }
}