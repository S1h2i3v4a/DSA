/*
QUESTION:
Given two strings s and t, return number of distinct subsequences of s which equals t.

APPROACH:
- Memoization: 2D table `dp[m][n]`.

TIME COMPLEXITY: O(M * N).
SPACE COMPLEXITY: O(M * N) table + O(M) stack.
*/

import java.util.Arrays;

class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        int[][] dp = new int[m][n];
        for (int[] row : dp) Arrays.fill(row, -1);
        return solve(m - 1, n - 1, s, t, dp);
    }

    private int solve(int i, int j, String s, String t, int[][] dp) {
        if (j < 0) return 1;
        if (i < 0) return 0;
        if (dp[i][j] != -1) return dp[i][j];

        if (s.charAt(i) == t.charAt(j)) {
            return dp[i][j] = solve(i - 1, j - 1, s, t, dp) + solve(i - 1, j, s, t, dp);
        }
        return dp[i][j] = solve(i - 1, j, s, t, dp);
    }
}
