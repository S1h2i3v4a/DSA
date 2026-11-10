/*
QUESTION:
Given a string s, find the minimum number of insertions to make s a palindrome.

APPROACH:
- The longest palindromic subsequence remains untouched.
- Minimum insertions needed = Length(s) - LongestPalindromicSubsequence(s).
- LPS is computed via space-optimized LCS(s, reverse(s)).

TIME COMPLEXITY: O(N^2).
SPACE COMPLEXITY: O(N) auxiliary space.
*/

class Solution {
    public int minInsertions(String s) {
        int n = s.length();
        int lps = longestPalindromeSubseq(s);
        return n - lps;
    }

    private int longestPalindromeSubseq(String s) {
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
