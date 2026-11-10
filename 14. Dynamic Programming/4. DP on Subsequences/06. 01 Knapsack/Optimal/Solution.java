/*
QUESTION:
0/1 Knapsack problem with capacity W.

APPROACH:
- Space-Optimized 1D Tabulation:
- Single 1D array of size `W + 1`, updated right-to-left from W down to wt[i].

TIME COMPLEXITY: O(N * W).
SPACE COMPLEXITY: O(W) 1D array.
*/

class Solution {
    public int knapSack(int W, int[] wt, int[] val, int n) {
        int[] dp = new int[W + 1];

        for (int i = 0; i < n; i++) {
            for (int w = W; w >= wt[i]; w--) {
                dp[w] = Math.max(dp[w], val[i] + dp[w - wt[i]]);
            }
        }

        return dp[W];
    }
}
