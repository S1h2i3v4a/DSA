/*
QUESTION:
Given an integer array nums, return true if you can partition the array into two subsets with equal sum.

APPROACH:
- Memoization: 2D memo table `dp[n][target + 1]`.

TIME COMPLEXITY: O(N * Target) where Target = sum / 2.
SPACE COMPLEXITY: O(N * Target) + O(N) stack.
*/

import java.util.Arrays;

class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int x : nums) sum += x;
        if (sum % 2 != 0) return false;
        int target = sum / 2;
        int[][] dp = new int[nums.length][target + 1];
        for (int[] row : dp) Arrays.fill(row, -1);
        return solve(nums.length - 1, target, nums, dp);
    }

    private boolean solve(int idx, int target, int[] nums, int[][] dp) {
        if (target == 0) return true;
        if (idx == 0) return nums[0] == target;
        if (dp[idx][target] != -1) return dp[idx][target] == 1;

        boolean notPick = solve(idx - 1, target, nums, dp);
        boolean pick = false;
        if (nums[idx] <= target) pick = solve(idx - 1, target - nums[idx], nums, dp);

        boolean res = notPick || pick;
        dp[idx][target] = res ? 1 : 0;
        return res;
    }
}
