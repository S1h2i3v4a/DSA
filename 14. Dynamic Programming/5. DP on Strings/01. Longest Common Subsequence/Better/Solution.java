/*
QUESTION:
Given two strings text1 and text2, return the length of their longest common subsequence.

APPROACH:
- Memoization: 2D table `dp[m][n]`.

TIME COMPLEXITY: O(M * N).
SPACE COMPLEXITY: O(M * N) table + O(M + N) stack.
*/

import java.util.Arrays;

class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m][n];
        for (int[] row : dp) Arrays.fill(row, -1);
        return solve(m - 1, n - 1, text1, text2, dp);
    }

    private int solve(int i, int j, String s1, String s2, int[][] dp) {
        if (i < 0 || j < 0) return 0;
        if (dp[i][j] != -1) return dp[i][j];

        if (s1.charAt(i) == s2.charAt(j)) {
            return dp[i][j] = 1 + solve(i - 1, j - 1, s1, s2, dp);
        }
        return dp[i][j] = Math.max(solve(i - 1, j, s1, s2, dp), solve(i, j - 1, s1, s2, dp));
    }
}
