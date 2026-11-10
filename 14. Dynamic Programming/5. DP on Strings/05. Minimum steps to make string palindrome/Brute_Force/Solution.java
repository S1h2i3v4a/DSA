/*
QUESTION:
Given a string s, find the minimum number of insertions to make s a palindrome.

APPROACH:
- Pure recursion: minInsertions(i, j).
- If s[i] == s[j], minInsertions(i + 1, j - 1).
- Else 1 + min(minInsertions(i + 1, j), minInsertions(i, j - 1)).

TIME COMPLEXITY: O(2^N).
SPACE COMPLEXITY: O(N) recursion stack.
*/

class Solution {
    public int minInsertions(String s) {
        return solve(0, s.length() - 1, s);
    }

    private int solve(int i, int j, String s) {
        if (i >= j) return 0;
        if (s.charAt(i) == s.charAt(j)) {
            return solve(i + 1, j - 1, s);
        }
        return 1 + Math.min(solve(i + 1, j, s), solve(i, j - 1, s));
    }
}
