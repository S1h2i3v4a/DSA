/*
QUESTION:
Houses are arranged in a circle. First and last houses are adjacent. Return maximum amount possible to rob.

APPROACH:
- Memoization for both linear sub-ranges [0, n-2] and [1, n-1].

TIME COMPLEXITY: O(N).
SPACE COMPLEXITY: O(N) dp array and recursion stack.
*/

import java.util.Arrays;

class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];

        int[] dp1 = new int[n];
        int[] dp2 = new int[n];
        Arrays.fill(dp1, -1);
        Arrays.fill(dp2, -1);

        int ans1 = solve(nums, n - 2, 0, dp1);
        int ans2 = solve(nums, n - 1, 1, dp2);
        return Math.max(ans1, ans2);
    }

    private int solve(int[] nums, int i, int start, int[] dp) {
        if (i < start) return 0;
        if (i == start) return nums[start];
        if (dp[i] != -1) return dp[i];
        int pick = nums[i] + solve(nums, i - 2, start, dp);
        int notPick = solve(nums, i - 1, start, dp);
        return dp[i] = Math.max(pick, notPick);
    }
}
