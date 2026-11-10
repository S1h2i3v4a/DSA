/*
QUESTION:
Burst Balloons.

APPROACH:
- Memoization: 2D table `dp[n+2][n+2]`.

TIME COMPLEXITY: O(N^3).
SPACE COMPLEXITY: O(N^2) table + O(N) stack.
*/

import java.util.Arrays;

class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n + 2];
        arr[0] = 1;
        arr[n + 1] = 1;
        System.arraycopy(nums, 0, arr, 1, n);

        int[][] dp = new int[n + 2][n + 2];
        for (int[] row : dp) Arrays.fill(row, -1);
        return solve(1, n, arr, dp);
    }

    private int solve(int i, int j, int[] arr, int[][] dp) {
        if (i > j) return 0;
        if (dp[i][j] != -1) return dp[i][j];

        int maxi = Integer.MIN_VALUE;
        for (int k = i; k <= j; k++) {
            int coins = arr[i - 1] * arr[k] * arr[j + 1] + solve(i, k - 1, arr, dp) + solve(k + 1, j, arr, dp);
            maxi = Math.max(maxi, coins);
        }

        return dp[i][j] = maxi;
    }
}
