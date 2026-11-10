/*
QUESTION:
Given an integer array nums, return true if you can partition the array into two subsets with equal sum.

APPROACH:
- Space-Optimized Tabulation:
- 1D boolean array `dp` of size `target + 1`, updated backwards.

TIME COMPLEXITY: O(N * Target).
SPACE COMPLEXITY: O(Target) = O(Sum / 2).
*/

class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int x : nums) sum += x;
        if (sum % 2 != 0) return false;
        int target = sum / 2;

        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        for (int num : nums) {
            for (int t = target; t >= num; t--) {
                dp[t] = dp[t] || dp[t - num];
            }
        }

        return dp[target];
    }
}
