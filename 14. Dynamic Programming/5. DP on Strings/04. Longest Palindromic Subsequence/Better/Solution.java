/*
QUESTION:
Given a string s, find the longest palindromic subsequence length.

APPROACH:
- Memoization: 2D table `dp[n][n]`.

TIME COMPLEXITY: O(N^2).
SPACE COMPLEXITY: O(N^2) table + O(N) stack.
*/

import java.util.Arrays;

class Solution {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int[] row : dp) Arrays.fill(row, -1);
        return solve(0, n - 1, s, dp);
    }

    private int solve(int i, int j, String s, int[][] dp) {
        if (i == j) return 1;
        if (i > j) return 0;
        if (dp[i][j] != -1) return dp[i][j];

        if (s.charAt(i) == s.charAt(j)) {
            return dp[i][j] = 2 + solve(i + 1, j - 1, s, dp);
        }
        return dp[i][j] = Math.max(solve(i + 1, j, s, dp), solve(i, j - 1, s, dp));
    }
}
