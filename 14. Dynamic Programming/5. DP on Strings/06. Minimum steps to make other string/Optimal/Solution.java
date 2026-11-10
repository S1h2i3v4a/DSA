/*
QUESTION:
Given two strings word1 and word2, return minimum number of deletions to make them equal.

APPROACH:
- Find Longest Common Subsequence (LCS).
- Remaining characters in word1 must be deleted (len(word1) - LCS).
- Remaining characters in word2 must be deleted (len(word2) - LCS).
- Total deletions = len(word1) + len(word2) - 2 * LCS.

TIME COMPLEXITY: O(M * N).
SPACE COMPLEXITY: O(N) auxiliary space.
*/

class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int lcs = lcsSpaceOptimized(word1, word2);
        return (m - lcs) + (n - lcs);
    }

    private int lcsSpaceOptimized(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[] prev = new int[n + 1];

        for (int i = 1; i <= m; i++) {
            int[] curr = new int[n + 1];
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
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
