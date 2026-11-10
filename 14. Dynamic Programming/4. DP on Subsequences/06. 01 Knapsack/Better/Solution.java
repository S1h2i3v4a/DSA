/*
QUESTION:
0/1 Knapsack problem with capacity W.

APPROACH:
- Memoization: 2D table `dp[n][W + 1]`.

TIME COMPLEXITY: O(N * W).
SPACE COMPLEXITY: O(N * W) table + O(N) stack.
*/

import java.util.Arrays;

class Solution {
    public int knapSack(int W, int[] wt, int[] val, int n) {
        int[][] dp = new int[n][W + 1];
        for (int[] row : dp) Arrays.fill(row, -1);
        return solve(n - 1, W, wt, val, dp);
    }

    private int solve(int i, int w, int[] wt, int[] val, int[][] dp) {
        if (i < 0 || w == 0) return 0;
        if (dp[i][w] != -1) return dp[i][w];
        int notPick = solve(i - 1, w, wt, val, dp);
        int pick = 0;
        if (wt[i] <= w) pick = val[i] + solve(i - 1, w - wt[i], wt, val, dp);
        return dp[i][w] = Math.max(notPick, pick);
    }
}
