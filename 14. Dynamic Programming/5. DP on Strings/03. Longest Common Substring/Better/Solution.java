/*
QUESTION:
Given two strings s1 and s2, find the length of the longest common substring.

APPROACH:
- 2D DP Table:
- `dp[i][j]` = length of common substring ending at s1[i-1] and s2[j-1].
- If characters match: `dp[i][j] = 1 + dp[i-1][j-1]`; else 0.

TIME COMPLEXITY: O(M * N).
SPACE COMPLEXITY: O(M * N) dp table.
*/

class Solution {
    public int longestCommonSubstr(String s1, String s2, int n, int m) {
        int[][] dp = new int[n + 1][m + 1];
        int maxLen = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    maxLen = Math.max(maxLen, dp[i][j]);
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        return maxLen;
    }
}
