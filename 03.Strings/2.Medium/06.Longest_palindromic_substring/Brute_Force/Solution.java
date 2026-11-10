/*
QUESTION:-
Given a string s, return the longest palindromic substring in s.

Example:
Input: s = "babad"
Output: "bab"
Explanation: "aba" is also a valid answer.
*/

/*
APPROACH:-
Check all substrings: Generate every substring O(N^2) and check whether each is a palindrome in O(N). Keep track of the longest palindrome found.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        String longest = "";
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (isPalindrome(s, i, j) && (j - i + 1) > longest.length()) {
                    longest = s.substring(i, j + 1);
                }
            }
        }
        return longest;
    }

    private boolean isPalindrome(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) return false;
        }
        return true;
    }
}

// TIME COMPLEXITY = O(N^3)
// SPACE COMPLEXITY = O(1)
