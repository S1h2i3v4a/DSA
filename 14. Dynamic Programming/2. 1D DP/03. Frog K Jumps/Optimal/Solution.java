/*
QUESTION:
Geek can jump anywhere from 1 to k steps. Find the minimum energy to reach stair n-1.

APPROACH:
- Tabulation (Bottom-Up DP):
- Build table `dp[i]` iteratively from 0 to n-1.
- `dp[i] = min(dp[i - j] + abs(height[i] - height[i - j]))` for 1 <= j <= k.

TIME COMPLEXITY: O(N * K).
SPACE COMPLEXITY: O(N) for dp array (or O(K) with circular buffer).
*/

class Solution {
    public int minimizeCost(int[] height, int n, int k) {
        int[] dp = new int[n];
        dp[0] = 0;

        for (int i = 1; i < n; i++) {
            int minSteps = Integer.MAX_VALUE;
            for (int j = 1; j <= k; j++) {
                if (i - j >= 0) {
                    int jump = dp[i - j] + Math.abs(height[i] - height[i - j]);
                    minSteps = Math.min(minSteps, jump);
                }
            }
            dp[i] = minSteps;
        }

        return dp[n - 1];
    }
}
