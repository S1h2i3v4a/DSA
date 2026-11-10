/*
QUESTION:
Given two strings haystack and needle, return index of first occurrence of needle in haystack, or -1 if not found.

APPROACH:
- Naive string search: for each starting position i in haystack, check if substring matches needle.

TIME COMPLEXITY: O((N - M + 1) * M) where N = haystack length, M = needle length.
SPACE COMPLEXITY: O(1) constant auxiliary space.
*/

class Solution {
    public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) return 0;
        int n = haystack.length();
        int m = needle.length();

        for (int i = 0; i <= n - m; i++) {
            int j = 0;
            while (j < m && haystack.charAt(i + j) == needle.charAt(j)) {
                j++;
            }
            if (j == m) return i;
        }

        return -1;
    }
}
