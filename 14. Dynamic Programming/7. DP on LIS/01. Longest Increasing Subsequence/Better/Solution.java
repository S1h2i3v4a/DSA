/*
QUESTION:
Longest Increasing Subsequence (LIS).

APPROACH:
- 1D DP Tabulation:
- `dp[i]` represents length of LIS ending at index `i`.
- `dp[i] = 1 + max(dp[prev])` for all 0 <= prev < i where nums[prev] < nums[i].

TIME COMPLEXITY: O(N^2).
SPACE COMPLEXITY: O(N).
*/

import java.util.Arrays;

class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int maxLen = 1;

        for (int i = 0; i < n; i++) {
            for (int prev = 0; prev < i; prev++) {
                if (nums[prev] < nums[i]) {
                    dp[i] = Math.max(dp[i], 1 + dp[prev]);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }
}
