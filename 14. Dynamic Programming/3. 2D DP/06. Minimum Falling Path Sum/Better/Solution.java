/*
QUESTION:
Given an n x n array of integers matrix, return minimum sum of any falling path.

APPROACH:
- Memoization: 2D table `dp[n][n]` storing minimum falling path sum ending at (r, c).

TIME COMPLEXITY: O(N^2).
SPACE COMPLEXITY: O(N^2) table + O(N) recursion stack.
*/

import java.util.Arrays;

class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int[][] dp = new int[n][n];
        for (int[] row : dp) Arrays.fill(row, -1);

        int ans = Integer.MAX_VALUE;
        for (int c = 0; c < n; c++) {
            ans = Math.min(ans, solve(n - 1, c, matrix, dp, n));
        }
        return ans;
    }

    private int solve(int r, int c, int[][] matrix, int[][] dp, int n) {
        if (c < 0 || c >= n) return (int) 1e9;
        if (r == 0) return matrix[0][c];
        if (dp[r][c] != -1) return dp[r][c];

        int upLeft = solve(r - 1, c - 1, matrix, dp, n);
        int up = solve(r - 1, c, matrix, dp, n);
        int upRight = solve(r - 1, c + 1, matrix, dp, n);

        return dp[r][c] = matrix[r][c] + Math.min(up, Math.min(upLeft, upRight));
    }
}
