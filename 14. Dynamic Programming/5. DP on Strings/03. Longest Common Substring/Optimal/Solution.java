/*
QUESTION:
Given two strings s1 and s2, find the length of the longest common substring.

APPROACH:
- Space-Optimized 1D Tabulation:
- Maintain 1D array of size `m + 1`, updated backwards to preserve `dp[j - 1]`.

TIME COMPLEXITY: O(M * N).
SPACE COMPLEXITY: O(M) auxiliary space.
*/

class Solution {
    public int longestCommonSubstr(String s1, String s2, int n, int m) {
        int[] dp = new int[m + 1];
        int maxLen = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = m; j >= 1; j--) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[j] = 1 + dp[j - 1];
                    maxLen = Math.max(maxLen, dp[j]);
                } else {
                    dp[j] = 0;
                }
            }
        }

        return maxLen;
    }
}
