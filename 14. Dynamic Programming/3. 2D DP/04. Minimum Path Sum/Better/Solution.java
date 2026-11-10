/*
QUESTION:
Given an m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes sum.

APPROACH:
- Memoization: Store computed min sums in `dp[m][n]`.

TIME COMPLEXITY: O(M * N).
SPACE COMPLEXITY: O(M * N) table + O(M + N) recursion stack.
*/

import java.util.Arrays;

class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int[] row : dp) Arrays.fill(row, -1);
        return solve(m - 1, n - 1, grid, dp);
    }

    private int solve(int r, int c, int[][] grid, int[][] dp) {
        if (r == 0 && c == 0) return grid[0][0];
        if (r < 0 || c < 0) return (int) 1e9;
        if (dp[r][c] != -1) return dp[r][c];
        int up = grid[r][c] + solve(r - 1, c, grid, dp);
        int left = grid[r][c] + solve(r, c - 1, grid, dp);
        return dp[r][c] = Math.min(up, left);
    }
}
