/*
QUESTION:
Given two strings text1 and text2, return the length of their longest common subsequence.

APPROACH:
- Pure recursion:
- If text1[i] == text2[j], 1 + solve(i - 1, j - 1).
- Else max(solve(i - 1, j), solve(i, j - 1)).

TIME COMPLEXITY: O(2^(M+N)).
SPACE COMPLEXITY: O(M + N) recursion stack.
*/

class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        return solve(text1.length() - 1, text2.length() - 1, text1, text2);
    }

    private int solve(int i, int j, String s1, String s2) {
        if (i < 0 || j < 0) return 0;
        if (s1.charAt(i) == s2.charAt(j)) {
            return 1 + solve(i - 1, j - 1, s1, s2);
        }
        return Math.max(solve(i - 1, j, s1, s2), solve(i, j - 1, s1, s2));
    }
}
