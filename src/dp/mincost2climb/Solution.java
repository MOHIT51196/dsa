package dp.mincost2climb;

import java.util.List;
import java.util.PriorityQueue;

public class Solution {

    int minCostClimbingStairs(List<Integer> costs, int n){
        if(n > costs.size()) throw new IllegalArgumentException("position should be less than total number of steps");
        return n > 1 ? Math.min(costs.get(n-1) + minCostClimbingStairs(costs, n-1), costs.get(n-2) + minCostClimbingStairs(costs, n-2) ) : 0;
    }

    public static void main(String[] args) {

        var costs = List.of(
                2, 18, 6, 2, -1, 3, 7
        );
        int pos = 6;
        int minCost = new Solution().minCostClimbingStairs(costs, pos);
        System.out.println("Min cost to climb to " + pos + "th step = " + minCost);
    }
}
