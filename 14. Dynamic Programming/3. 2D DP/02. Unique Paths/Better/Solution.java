/*
QUESTION:
Find total number of unique paths from (0,0) to (m-1, n-1) on an m x n grid.

APPROACH:
- Memoization: 2D table `dp[m][n]` storing unique paths to (r, c).

TIME COMPLEXITY: O(M * N).
SPACE COMPLEXITY: O(M * N) table + O(M + N) recursion stack.
*/

import java.util.Arrays;

class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int[] row : dp) Arrays.fill(row, -1);
        return solve(m - 1, n - 1, dp);
    }

    private int solve(int r, int c, int[][] dp) {
        if (r == 0 && c == 0) return 1;
        if (r < 0 || c < 0) return 0;
        if (dp[r][c] != -1) return dp[r][c];
        return dp[r][c] = solve(r - 1, c, dp) + solve(r, c - 1, dp);
    }
}
