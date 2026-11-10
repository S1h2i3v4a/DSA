/*
QUESTION:
Find the longest happy prefix of string s.

APPROACH:
- Construct KMP LPS (Longest Prefix Suffix) array for s.
- The value `lps[n - 1]` gives the exact length of the longest proper prefix that is also a suffix.

TIME COMPLEXITY: O(N) linear scan.
SPACE COMPLEXITY: O(N) for LPS array.
*/

class Solution {
    public String longestPrefix(String s) {
        int n = s.length();
        int[] lps = new int[n];
        int len = 0, i = 1;

        while (i < n) {
            if (s.charAt(i) == s.charAt(len)) {
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

        return s.substring(0, lps[n - 1]);
    }
}
