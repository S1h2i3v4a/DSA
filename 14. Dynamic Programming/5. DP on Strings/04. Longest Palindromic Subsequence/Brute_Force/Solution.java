/*
QUESTION:
Given a string s, find the longest palindromic subsequence's length in s.

APPROACH:
- Pure recursion with two pointers i = 0, j = n - 1.
- If s[i] == s[j], return 2 + solve(i + 1, j - 1).
- Else return max(solve(i + 1, j), solve(i, j - 1)).

TIME COMPLEXITY: O(2^N).
SPACE COMPLEXITY: O(N) recursion stack.
*/

class Solution {
    public int longestPalindromeSubseq(String s) {
        return solve(0, s.length() - 1, s);
    }

    private int solve(int i, int j, String s) {
        if (i == j) return 1;
        if (i > j) return 0;
        if (s.charAt(i) == s.charAt(j)) {
            return 2 + solve(i + 1, j - 1, s);
        }
        return Math.max(solve(i + 1, j, s), solve(i, j - 1, s));
    }
}
