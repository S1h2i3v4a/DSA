/*
QUESTION:
Given two strings s1 and s2, find the length of the longest common substring.

APPROACH:
- Generate all substrings of s1 and check if present in s2. Return max length.

TIME COMPLEXITY: O(M^2 * N).
SPACE COMPLEXITY: O(1).
*/

class Solution {
    public int longestCommonSubstr(String s1, String s2, int n, int m) {
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                String sub = s1.substring(i, j);
                if (s2.contains(sub)) {
                    maxLen = Math.max(maxLen, sub.length());
                }
            }
        }
        return maxLen;
    }
}
