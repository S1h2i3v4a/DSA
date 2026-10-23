/*
QUESTION:
A string is called a happy prefix if it is a non-empty prefix that is also a suffix (excluding itself).

Given a string s, return the longest happy prefix of s. Return an empty string "" if no such prefix exists.

Example 1:
Input: s = "level"
Output: "l"
Explanation: s contains 4 prefixes excluding itself ("l", "le", "lev", "leve") and suffixes ("l", "el", "vel", "evel"). The largest prefix that is also a suffix is "l".

Approach:
- We can solve this problem using the KMP (Knuth-Morris-Pratt) algorithm.
- First, we calculate the LPS (Longest Prefix Suffix) array for the given string s.
- The last value in the LPS array, lps[n-1], represents the length of the longest prefix that is also a suffix.
- We extract the corresponding substring from s and return it as the longest happy prefix.

Complexity Analysis:
- The time complexity of this solution is O(n), where n is the length of the string s.
- The space complexity is O(n) to store the LPS array.

CODE:
*/

import java.io.*;
import java.util.*;

// CODE:-
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
