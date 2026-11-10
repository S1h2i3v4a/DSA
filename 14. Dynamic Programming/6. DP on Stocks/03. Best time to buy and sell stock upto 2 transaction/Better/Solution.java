/*
QUESTION:
Maximum profit with at most two transactions.

APPROACH:
- Memoization: 3D table `dp[n][2][3]`.

TIME COMPLEXITY: O(N * 2 * 3) = O(N).
SPACE COMPLEXITY: O(N) dp table + O(N) stack.
*/

import java.util.Arrays;

class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n][2][3];
        for (int[][] mat : dp) {
            for (int[] row : mat) Arrays.fill(row, -1);
        }
        return solve(0, 1, 2, prices, dp);
    }

    private int solve(int i, int canBuy, int cap, int[] prices, int[][][] dp) {
        if (i == prices.length || cap == 0) return 0;
        if (dp[i][canBuy][cap] != -1) return dp[i][canBuy][cap];

        if (canBuy == 1) {
            int buy = -prices[i] + solve(i + 1, 0, cap, prices, dp);
            int skip = solve(i + 1, 1, cap, prices, dp);
            return dp[i][canBuy][cap] = Math.max(buy, skip);
        } else {
            int sell = prices[i] + solve(i + 1, 1, cap - 1, prices, dp);
            int skip = solve(i + 1, 0, cap, prices, dp);
            return dp[i][canBuy][cap] = Math.max(sell, skip);
        }
    }
}
