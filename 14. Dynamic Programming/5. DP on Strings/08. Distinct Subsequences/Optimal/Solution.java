/*
QUESTION:
Given two strings s and t, return number of distinct subsequences of s which equals t.

APPROACH:
- Space-Optimized 1D Tabulation:
- `dp[j]` represents distinct subsequences matching prefix of length `j`.
- Update backwards: `if (s[i-1] == t[j-1]) dp[j] += dp[j - 1]`.

TIME COMPLEXITY: O(M * N).
SPACE COMPLEXITY: O(N) 1D array.
*/

class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;

        for (int i = 1; i <= m; i++) {
            for (int j = n; j >= 1; j--) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[j] += dp[j - 1];
                }
            }
        }

        return dp[n];
    }
}
