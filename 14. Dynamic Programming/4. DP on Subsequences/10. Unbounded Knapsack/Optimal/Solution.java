/*
QUESTION:
Unbounded knapsack with capacity W.

APPROACH:
- Space-Optimized 1D Tabulation:
- `dp[w]` stores max value for capacity `w`.
- Forward update allows unlimited item reuse: `dp[w] = max(dp[w], val[i] + dp[w - wt[i]])`.

TIME COMPLEXITY: O(N * W).
SPACE COMPLEXITY: O(W) 1D array.
*/

class Solution {
    public int knapSack(int N, int W, int[] val, int[] wt) {
        int[] dp = new int[W + 1];

        for (int i = 0; i < N; i++) {
            for (int w = wt[i]; w <= W; w++) {
                dp[w] = Math.max(dp[w], val[i] + dp[w - wt[i]]);
            }
        }

        return dp[W];
    }
}
