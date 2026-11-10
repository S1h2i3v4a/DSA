/*
QUESTION:
Wildcard Pattern Matching with '?' and '*'.

APPROACH:
- Memoization: 2D table `dp[m][n]`.

TIME COMPLEXITY: O(M * N).
SPACE COMPLEXITY: O(M * N) table + O(M + N) stack.
*/

import java.util.Arrays;

class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        int[][] dp = new int[m][n];
        for (int[] row : dp) Arrays.fill(row, -1);
        return solve(m - 1, n - 1, s, p, dp);
    }

    private boolean solve(int i, int j, String s, String p, int[][] dp) {
        if (i < 0 && j < 0) return true;
        if (j < 0) return false;
        if (i < 0) {
            for (int k = 0; k <= j; k++) {
                if (p.charAt(k) != '*') return false;
            }
            return true;
        }
        if (dp[i][j] != -1) return dp[i][j] == 1;

        boolean result = false;
        if (p.charAt(j) == '?' || s.charAt(i) == p.charAt(j)) {
            result = solve(i - 1, j - 1, s, p, dp);
        } else if (p.charAt(j) == '*') {
            result = solve(i, j - 1, s, p, dp) || solve(i - 1, j, s, p, dp);
        }

        dp[i][j] = result ? 1 : 0;
        return result;
    }
}
