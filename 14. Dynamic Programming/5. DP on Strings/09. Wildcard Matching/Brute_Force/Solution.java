/*
QUESTION:
Given input string s and pattern p, implement wildcard pattern matching with support for '?' (matches any single char) and '*' (matches any sequence of chars).

APPROACH:
- Pure recursion:
- If p[j] == '?' or p[j] == s[i], solve(i - 1, j - 1).
- If p[j] == '*', either match 0 chars solve(i, j - 1) or match >= 1 char solve(i - 1, j).

TIME COMPLEXITY: O(2^(M+N)).
SPACE COMPLEXITY: O(M + N) recursion stack.
*/

class Solution {
    public boolean isMatch(String s, String p) {
        return solve(s.length() - 1, p.length() - 1, s, p);
    }

    private boolean solve(int i, int j, String s, String p) {
        if (i < 0 && j < 0) return true;
        if (j < 0) return false;
        if (i < 0) {
            for (int k = 0; k <= j; k++) {
                if (p.charAt(k) != '*') return false;
            }
            return true;
        }

        if (p.charAt(j) == '?' || s.charAt(i) == p.charAt(j)) {
            return solve(i - 1, j - 1, s, p);
        }
        if (p.charAt(j) == '*') {
            return solve(i, j - 1, s, p) || solve(i - 1, j, s, p);
        }

        return false;
    }
}
