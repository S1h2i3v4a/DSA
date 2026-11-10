/*
QUESTION:
Given an array `nums` of house values, return the maximum amount of money you can rob without robbing two adjacent houses.

APPROACH:
- Memoization (Top-down DP):
- Cache results of subproblems in `dp` array of size n.

TIME COMPLEXITY: O(N).
SPACE COMPLEXITY: O(N) recursion stack + dp table.
*/

import java.util.Arrays;

class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return solve(n - 1, nums, dp);
    }

    private int solve(int i, int[] nums, int[] dp) {
        if (i < 0) return 0;
        if (i == 0) return nums[0];
        if (dp[i] != -1) return dp[i];
        int pick = nums[i] + solve(i - 2, nums, dp);
        int notPick = solve(i - 1, nums, dp);
        return dp[i] = Math.max(pick, notPick);
    }
}
