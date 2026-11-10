/*
QUESTION:
Best Time to Buy and Sell Stock with Cooldown.

APPROACH:
- Memoization: 2D table `dp[n][2]`.

TIME COMPLEXITY: O(N).
SPACE COMPLEXITY: O(N) table + O(N) stack.
*/

import java.util.Arrays;

class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int[] row : dp) Arrays.fill(row, -1);
        return solve(0, 1, prices, dp);
    }

    private int solve(int i, int canBuy, int[] prices, int[][] dp) {
        if (i >= prices.length) return 0;
        if (dp[i][canBuy] != -1) return dp[i][canBuy];

        if (canBuy == 1) {
            int buy = -prices[i] + solve(i + 1, 0, prices, dp);
            int skip = solve(i + 1, 1, prices, dp);
            return dp[i][canBuy] = Math.max(buy, skip);
        } else {
            int sell = prices[i] + solve(i + 2, 1, prices, dp);
            int skip = solve(i + 1, 0, prices, dp);
            return dp[i][canBuy] = Math.max(sell, skip);
        }
    }
}
