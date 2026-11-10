/*
QUESTION:
Given two strings s and t, return number of distinct subsequences of s which equals t.

APPROACH:
- Pure recursion: at index i of s and j of t:
  - If s[i] == t[j], ways = solve(i - 1, j - 1) + solve(i - 1, j).
  - Else solve(i - 1, j).

TIME COMPLEXITY: O(2^M).
SPACE COMPLEXITY: O(M) recursion stack.
*/

class Solution {
    public int numDistinct(String s, String t) {
        return solve(s.length() - 1, t.length() - 1, s, t);
    }

    private int solve(int i, int j, String s, String t) {
        if (j < 0) return 1;
        if (i < 0) return 0;

        if (s.charAt(i) == t.charAt(j)) {
            return solve(i - 1, j - 1, s, t) + solve(i - 1, j, s, t);
        }
        return solve(i - 1, j, s, t);
    }
}
