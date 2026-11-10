/*
QUESTION:
Given a string s, find the longest palindromic subsequence length.

APPROACH:
- Space-Optimized LCS:
- LPS(s) is equivalent to LCS(s, reverse(s)).
- Use two 1D rows for space-optimized LCS calculation.

TIME COMPLEXITY: O(N^2).
SPACE COMPLEXITY: O(N) auxiliary space.
*/

class Solution {
    public int longestPalindromeSubseq(String s) {
        String rev = new StringBuilder(s).reverse().toString();
        int n = s.length();
        int[] prev = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            int[] curr = new int[n + 1];
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == rev.charAt(j - 1)) {
                    curr[j] = 1 + prev[j - 1];
                } else {
                    curr[j] = Math.max(prev[j], curr[j - 1]);
                }
            }
            prev = curr;
        }

        return prev[n];
    }
}
