/*
QUESTION:
Unbounded knapsack with capacity W.

APPROACH:
- Memoization: 2D table `dp[N][W + 1]`.

TIME COMPLEXITY: O(N * W).
SPACE COMPLEXITY: O(N * W) table + O(W) stack.
*/

import java.util.Arrays;

class Solution {
    public int knapSack(int N, int W, int[] val, int[] wt) {
        int[][] dp = new int[N][W + 1];
        for (int[] row : dp) Arrays.fill(row, -1);
        return solve(N - 1, W, val, wt, dp);
    }

    private int solve(int i, int w, int[] val, int[] wt, int[][] dp) {
        if (i < 0 || w == 0) return 0;
        if (dp[i][w] != -1) return dp[i][w];
        int notTake = solve(i - 1, w, val, wt, dp);
        int take = 0;
        if (wt[i] <= w) take = val[i] + solve(i, w - wt[i], val, wt, dp);
        return dp[i][w] = Math.max(notTake, take);
    }
}
