/*
Question:-
Given a string `s`, the task is to find the longest palindromic substring in `s`.

Example 1:
Input: s = "babad"
Output: "bab"
Explanation: "aba" is also a valid answer.

Example 2:
Input: s = "cbbd"
Output: "bb"

Approach:
1. We define a helper function `expandFromCenter` that takes a string `s`, and two indices `start` and `end` as input.
2. The function expands from the center and checks if the substring from `start` to `end` is a palindrome.
3. If the length of the current palindrome is greater than the maximum length seen so far (`maxLen`), we update the maximum length and the corresponding start and end indices (`ans_start` and `ans_end`).
4. We iterate over each character of the string `s` and consider it as a potential center for the palindrome.
5. We call `expandFromCenter` twice for each character - once for considering odd-length palindromes and once for even-length palindromes.
6. Finally, we return the substring of `s` that corresponds to the longest palindromic substring.

Code:*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;

        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
