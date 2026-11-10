/*
QUESTION:
A string is called a happy prefix if it is a non-empty prefix that is also a suffix (excluding itself).
Find the longest happy prefix.

APPROACH:
- Check all prefix lengths from n - 1 down to 1.
- If prefix equals suffix of same length, return it immediately.

TIME COMPLEXITY: O(N^2) substring slicing and comparisons.
SPACE COMPLEXITY: O(N) substring memory.
*/

class Solution {
    public String longestPrefix(String s) {
        int n = s.length();
        for (int len = n - 1; len >= 1; len--) {
            if (s.substring(0, len).equals(s.substring(n - len))) {
                return s.substring(0, len);
            }
        }
        return "";
    }
}
