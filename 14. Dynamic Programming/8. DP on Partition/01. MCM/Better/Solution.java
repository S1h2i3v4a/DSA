/*
QUESTION:
Matrix Chain Multiplication (MCM).

APPROACH:
- Memoization: 2D table `dp[n][n]`.

TIME COMPLEXITY: O(N^3).
SPACE COMPLEXITY: O(N^2) table + O(N) stack.
*/

import java.util.Arrays;

class Solution {
    public int matrixMultiplication(int[] arr, int n) {
        int[][] dp = new int[n][n];
        for (int[] row : dp) Arrays.fill(row, -1);
        return solve(1, n - 1, arr, dp);
    }

    private int solve(int i, int j, int[] arr, int[][] dp) {
        if (i == j) return 0;
        if (dp[i][j] != -1) return dp[i][j];

        int mini = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            int cost = solve(i, k, arr, dp) + solve(k + 1, j, arr, dp) + arr[i - 1] * arr[k] * arr[j];
            mini = Math.min(mini, cost);
        }

        return dp[i][j] = mini;
    }
}
