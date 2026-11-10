/*
QUESTION:
Given two strings s1 and s2, find and return any longest common subsequence string.

APPROACH:
- Construct full DP table `dp[m+1][n+1]`.
- Backtrack from (m, n) to (0, 0):
  If s1[i-1] == s2[j-1], prepend char and move (i-1, j-1).
  Else move to max(dp[i-1][j], dp[i][j-1]).

TIME COMPLEXITY: O(M * N).
SPACE COMPLEXITY: O(M * N) dp table.
*/

class Solution {
    public String findLCS(int m, int n, String s1, String s2) {
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int i = m, j = n;
        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                sb.append(s1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        return sb.reverse().toString();
    }
}
