/*
QUESTION:
Count all subsets of arr with sum equal to target.

APPROACH:
- Memoization: 2D table `dp[n][target + 1]`.

TIME COMPLEXITY: O(N * Target).
SPACE COMPLEXITY: O(N * Target) + O(N) stack.
*/

import java.util.Arrays;

class Solution {
    private static final int MOD = 1000000007;

    public int findWays(int[] arr, int target) {
        int n = arr.length;
        int[][] dp = new int[n][target + 1];
        for (int[] row : dp) Arrays.fill(row, -1);
        return solve(n - 1, target, arr, dp);
    }

    private int solve(int i, int target, int[] arr, int[][] dp) {
        if (i == 0) {
            if (target == 0 && arr[0] == 0) return 2;
            if (target == 0 || target == arr[0]) return 1;
            return 0;
        }
        if (dp[i][target] != -1) return dp[i][target];

        int notPick = solve(i - 1, target, arr, dp);
        int pick = 0;
        if (arr[i] <= target) pick = solve(i - 1, target - arr[i], arr, dp);

        return dp[i][target] = (notPick + pick) % MOD;
    }
}
