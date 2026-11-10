/*
QUESTION:
Geek can jump anywhere from 1 to k steps. Find the minimum energy to reach stair n-1.

APPROACH:
- Memoization (Top-down DP):
- Cache results for each step in `dp` array of size n.

TIME COMPLEXITY: O(N * K).
SPACE COMPLEXITY: O(N) recursion stack and dp array.
*/

import java.util.Arrays;

class Solution {
    public int minimizeCost(int[] height, int n, int k) {
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return solve(n - 1, height, k, dp);
    }

    private int solve(int i, int[] height, int k, int[] dp) {
        if (i == 0) return 0;
        if (dp[i] != -1) return dp[i];
        int minSteps = Integer.MAX_VALUE;
        for (int j = 1; j <= k; j++) {
            if (i - j >= 0) {
                int jump = solve(i - j, height, k, dp) + Math.abs(height[i] - height[i - j]);
                minSteps = Math.min(minSteps, jump);
            }
        }
        return dp[i] = minSteps;
    }
}
