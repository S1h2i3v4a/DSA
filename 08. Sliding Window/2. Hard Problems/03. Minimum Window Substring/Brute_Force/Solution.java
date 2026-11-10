/*
QUESTION:
Given two strings `s` and `t` of lengths `m` and `n` respectively, return the minimum window substring of `s`
such that every character in `t` (including duplicates) is included in the window.
If there is no such substring, return the empty string `""`.

APPROACH:
- Inspect all substrings `s[i...j]`.
- For each substring, count character frequencies and verify if it covers all characters in `t`.
- Among valid substrings, find the one with the smallest length.

TIME COMPLEXITY: O(M^2 * 256) where M is length of s.
SPACE COMPLEXITY: O(256) for character frequency comparisons.
*/

class Solution {
    private static boolean covers(int[] sCount, int[] tCount) {
        for (int i = 0; i < 256; i++) {
            if (sCount[i] < tCount[i]) return false;
        }
        return true;
    }

    public String minWindow(String s, String t) {
        int m = s.length(), n = t.length();
        if (m < n) return "";

        int[] tCount = new int[256];
        for (char c : t.toCharArray()) tCount[c]++;

        int minLen = Integer.MAX_VALUE;
        int startIdx = -1;

        for (int i = 0; i < m; i++) {
            int[] sCount = new int[256];
            for (int j = i; j < m; j++) {
                sCount[s.charAt(j)]++;
                if (covers(sCount, tCount)) {
                    int len = j - i + 1;
                    if (len < minLen) {
                        minLen = len;
                        startIdx = i;
                    }
                    break;
                }
            }
        }
        return (startIdx == -1) ? "" : s.substring(startIdx, startIdx + minLen);
    }
}
