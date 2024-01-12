package problems.jump_game.two;

public class Solution {
    public int jump(int[] nums) {
        
        int l = 0;
        int r = 0;
        int count = 0;
        while(r < nums.length - 1){
            int maxJump = 0;

            for(int i = l; i <= r; i++){
                if(i + nums[i] > maxJump){
                    maxJump = i + nums[i];
                }
            }
            count++;
            l = r + 1;
            r = maxJump;
        }
        return count;
    }
}