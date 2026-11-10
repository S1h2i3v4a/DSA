/*
QUESTION:
Given an array of non-negative integers and an integer k, determine if there exists a subset with sum equal to k.

APPROACH:
- Memoization: 2D array `dp[n][target + 1]` storing -1 (uncomputed), 1 (true), 0 (false).

TIME COMPLEXITY: O(N * Target).
SPACE COMPLEXITY: O(N * Target) + O(N) stack.
*/

import java.util.Arrays;

class Solution {
    public boolean isSubsetSum(int[] arr, int target) {
        int n = arr.length;
        int[][] dp = new int[n][target + 1];
        for (int[] row : dp) Arrays.fill(row, -1);
        return solve(n - 1, target, arr, dp);
    }

    private boolean solve(int index, int target, int[] arr, int[][] dp) {
        if (target == 0) return true;
        if (index == 0) return arr[0] == target;
        if (dp[index][target] != -1) return dp[index][target] == 1;

        boolean notPick = solve(index - 1, target, arr, dp);
        boolean pick = false;
        if (arr[index] <= target) {
            pick = solve(index - 1, target - arr[index], arr, dp);
        }

        boolean res = notPick || pick;
        dp[index][target] = res ? 1 : 0;
        return res;
    }
}
