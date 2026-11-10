/*
QUESTION:
Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences.

APPROACH:
- Compute full LCS 2D table `dp[m+1][n+1]`.
- Backtrack from (m, n) down to (0, 0):
  - If str1[i-1] == str2[j-1], add char once, i--, j--.
  - Else if dp[i-1][j] > dp[i][j-1], add str1[i-1], i--.
  - Else add str2[j-1], j--.
- Reverse string builder result.

TIME COMPLEXITY: O(M * N).
SPACE COMPLEXITY: O(M * N) dp table.
*/

class Solution {
    public String shortestCommonSupersequence(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int i = m, j = n;
        while (i > 0 && j > 0) {
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                sb.append(str1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                sb.append(str1.charAt(i - 1));
                i--;
            } else {
                sb.append(str2.charAt(j - 1));
                j--;
            }
        }

        while (i > 0) {
            sb.append(str1.charAt(i - 1));
            i--;
        }
        while (j > 0) {
            sb.append(str2.charAt(j - 1));
            j--;
        }

        return sb.reverse().toString();
    }
}
