package problems.coin_change;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Stack;

class Solution {
    public int coinChange(int[] coins, int amount) {
        final int INF = amount + 1;
        int dp[] = new int[amount + 1]; // 0 .. amount
        Arrays.fill(dp, INF);
        dp[0] = 0;
        
        for(int i = 1; i<= amount; i++){
            for(int coin : coins){
                if(coin <= i){
                    dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
                }
            }
        }
        return dp[amount] != INF ? dp[amount] : -1;
    }
}