/*
QUESTION:
Given a triangle array, return the minimum path sum from top to bottom.

APPROACH:
- Memoization: 2D array `dp[n][n]` storing minimum total starting from (i, j).

TIME COMPLEXITY: O(N^2).
SPACE COMPLEXITY: O(N^2) dp table + O(N) recursion stack.
*/

import java.util.Arrays;
import java.util.List;

class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n];
        for (int[] row : dp) Arrays.fill(row, -1);
        return solve(0, 0, triangle, dp, n);
    }

    private int solve(int i, int j, List<List<Integer>> triangle, int[][] dp, int n) {
        if (i == n - 1) return triangle.get(i).get(j);
        if (dp[i][j] != -1) return dp[i][j];
        int down = triangle.get(i).get(j) + solve(i + 1, j, triangle, dp, n);
        int diagonal = triangle.get(i).get(j) + solve(i + 1, j + 1, triangle, dp, n);
        return dp[i][j] = Math.min(down, diagonal);
    }
}
