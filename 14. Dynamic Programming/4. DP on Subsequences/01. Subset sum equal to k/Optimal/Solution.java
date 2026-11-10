/*
QUESTION:
Given an array of non-negative integers and an integer k, determine if there exists a subset with sum equal to k.

APPROACH:
- Space-Optimized Tabulation:
- Maintain 1D boolean array `dp` of size `target + 1`.
- Update backwards to use previous row values: `dp[t] = dp[t] || dp[t - arr[i]]`.

TIME COMPLEXITY: O(N * Target).
SPACE COMPLEXITY: O(Target) 1D array.
*/

class Solution {
    public boolean isSubsetSum(int[] arr, int target) {
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        if (arr[0] <= target) dp[arr[0]] = true;

        for (int i = 1; i < arr.length; i++) {
            boolean[] next = new boolean[target + 1];
            next[0] = true;
            for (int t = 1; t <= target; t++) {
                boolean notPick = dp[t];
                boolean pick = (arr[i] <= t) ? dp[t - arr[i]] : false;
                next[t] = notPick || pick;
            }
            dp = next;
        }

        return dp[target];
    }
}
