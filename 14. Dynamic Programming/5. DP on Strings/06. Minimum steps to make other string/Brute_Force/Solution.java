/*
QUESTION:
Given two strings word1 and word2, return minimum number of steps to make word1 and word2 the same (only deletion allowed).

APPROACH:
- Pure recursion: minDelete(i, j).
- If match, minDelete(i - 1, j - 1).
- Else 1 + min(minDelete(i - 1, j), minDelete(i, j - 1)).

TIME COMPLEXITY: O(2^(M+N)).
SPACE COMPLEXITY: O(M + N) recursion stack.
*/

class Solution {
    public int minDistance(String word1, String word2) {
        return solve(word1.length() - 1, word2.length() - 1, word1, word2);
    }

    private int solve(int i, int j, String s1, String s2) {
        if (i < 0) return j + 1;
        if (j < 0) return i + 1;
        if (s1.charAt(i) == s2.charAt(j)) {
            return solve(i - 1, j - 1, s1, s2);
        }
        return 1 + Math.min(solve(i - 1, j, s1, s2), solve(i, j - 1, s1, s2));
    }
}
