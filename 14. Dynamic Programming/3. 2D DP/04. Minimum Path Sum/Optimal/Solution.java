/*
QUESTION:
Given an m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes sum.

APPROACH:
- Space-Optimized Tabulation:
- Maintain a single 1D array `dp` of size `n`.
- For row i: `dp[j] = grid[i][j] + min(dp[j], dp[j - 1])`.

TIME COMPLEXITY: O(M * N).
SPACE COMPLEXITY: O(N) space.
*/

class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] dp = new int[n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[j] = grid[0][0];
                } else {
                    int up = (i > 0) ? dp[j] : (int) 1e9;
                    int left = (j > 0) ? dp[j - 1] : (int) 1e9;
                    dp[j] = grid[i][j] + Math.min(up, left);
                }
            }
        }

        return dp[n - 1];
    }
}
