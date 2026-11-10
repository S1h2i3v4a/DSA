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
Dynamic Programming: Use a 2D boolean table dp[i][j] representing whether s[i..j] is a palindrome. dp[i][j] is true if s[i] == s[j] and (j - i < 2 or dp[i+1][j-1]).
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        if (n == 0) return "";
        boolean[][] dp = new boolean[n][n];
        int start = 0, maxLen = 1;

        for (int i = 0; i < n; i++) dp[i][i] = true;

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    if (len == 2 || dp[i + 1][j - 1]) {
                        dp[i][j] = true;
                        if (len > maxLen) {
                            start = i;
                            maxLen = len;
                        }
                    }
                }
            }
        }
        return s.substring(start, start + maxLen);
    }
}

// TIME COMPLEXITY = O(N^2)
// SPACE COMPLEXITY = O(N^2)
