/*
QUESTION:
Find first occurrence of needle in haystack.

APPROACH:
- KMP (Knuth-Morris-Pratt) Algorithm:
- Precompute LPS (Longest Prefix Suffix) array of needle.
- Match characters: when mismatch occurs, skip redundant comparisons using `j = lps[j - 1]`.

TIME COMPLEXITY: O(N + M) linear time.
SPACE COMPLEXITY: O(M) for LPS array.
*/

class Solution {
    public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) return 0;
        int[] lps = computeLPS(needle);
        int i = 0, j = 0;

        while (i < haystack.length()) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            }
            if (j == needle.length()) {
                return i - j;
            } else if (i < haystack.length() && haystack.charAt(i) != needle.charAt(j)) {
                if (j != 0) j = lps[j - 1];
                else i++;
            }
        }

        return -1;
    }

    private int[] computeLPS(String pat) {
        int[] lps = new int[pat.length()];
        int len = 0, i = 1;
        while (i < pat.length()) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) len = lps[len - 1];
                else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }
}
