package problems.binary_tree_max_path_sum;


class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }



public class Solution {
    
    int maxSum = Integer.MIN_VALUE;
    private int sum(TreeNode node){
        if(node == null) return 0;
        
        int leftSum = Math.max(sum(node.left), 0);
        int rightSum = Math.max(sum(node.right), 0);

        int newSum = node.val + leftSum + rightSum;
        maxSum = Math.max(newSum, maxSum);

        return node.val + Math.max(leftSum, rightSum);
    }
    public int maxPathSum(TreeNode root) {
        sum(root);
        return maxSum;
    }
}