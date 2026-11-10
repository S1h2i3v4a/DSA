/*
QUESTION:
Given an m x n grid with obstacles, find unique paths from top-left to bottom-right.

APPROACH:
- Memoization: Store computed path counts in `dp[m][n]`.

TIME COMPLEXITY: O(M * N).
SPACE COMPLEXITY: O(M * N) table + O(M + N) recursion stack.
*/

import java.util.Arrays;

class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        for (int[] row : dp) Arrays.fill(row, -1);
        return solve(m - 1, n - 1, obstacleGrid, dp);
    }

    private int solve(int r, int c, int[][] grid, int[][] dp) {
        if (r < 0 || c < 0 || grid[r][c] == 1) return 0;
        if (r == 0 && c == 0) return 1;
        if (dp[r][c] != -1) return dp[r][c];
        return dp[r][c] = solve(r - 1, c, grid, dp) + solve(r, c - 1, grid, dp);
    }
}
