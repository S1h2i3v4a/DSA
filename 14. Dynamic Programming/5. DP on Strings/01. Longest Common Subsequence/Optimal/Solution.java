/*
QUESTION:
Given two strings text1 and text2, return the length of their longest common subsequence.

APPROACH:
- Space-Optimized Tabulation:
- Maintain two rows `prev` and `curr` of size `n + 1`.

TIME COMPLEXITY: O(M * N).
SPACE COMPLEXITY: O(N) auxiliary space.
*/

class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[] prev = new int[n + 1];

        for (int i = 1; i <= m; i++) {
            int[] curr = new int[n + 1];
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
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
