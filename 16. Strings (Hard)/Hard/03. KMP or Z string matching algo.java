/*
QUESTION:
Given two strings needle and haystack, return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

Example 1:
Input: haystack = "sadbutsad", needle = "sad"
Output: 0
Explanation: "sad" occurs at index 0 and 6.
The first occurrence is at index 0, so we return 0.

Approach:
- We can solve this problem using the KMP (Knuth-Morris-Pratt) string matching algorithm.
- First, we calculate the LPS (Longest Prefix Suffix) array for the needle string.
- Then, we create a new string by concatenating needle, a special character (not present in either needle or haystack), and haystack.
- We iterate through the new string and compare characters using the LPS array to determine if there is a match.
- If the LPS value becomes equal to the length of the needle, it means we have found a match, and we return the starting index of the match in the haystack string.

Complexity Analysis:
- The time complexity of this solution is O(m + n), where m is the length of the needle and n is the length of the haystack.
- The space complexity is O(m + n) but can be reduced to O(n), where m is the length of the needle.

CODE:
*/

import java.io.*;
import java.util.*;

// CODE:-
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
