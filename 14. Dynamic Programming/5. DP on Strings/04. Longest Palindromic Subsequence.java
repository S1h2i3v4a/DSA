/*
QUESTION:
Given a string s, find the longest palindromic subsequence's length in s.

Example:
Input: s = "bbbab"
Output: 4
Explanation: One possible longest palindromic subsequence is "bbbb".

APPROACH:
- This problem can be solved using Dynamic Programming (DP).
- Initialize a 2D DP array 'dp' where dp[i][j] represents the length of 
  the longest palindromic subsequence in the substring s[i..j].
- For each character at index 'i' and each character at index 'j':
  - If s[i] == s[j], then dp[i][j] = dp[i+1][j-1] + 2
  - Else, dp[i][j] = max(dp[i+1][j], dp[i][j-1])
- The required answer will be dp[0][n-1], where 'n' is the length of 's'.

COMPLEXITY ANALYSIS:
- The time complexity of the solution is O(n^2), where 'n' is the length of the input string.
- The space complexity is O(n^2) for the dp array.

CODE:
*/

import java.io.*;
import java.util.*;

// CODE:-
class LongestPalindromicSubsequence {

    public static int longestPalindromeSubseq(String s) {
        String reversed = new StringBuilder(s).reverse().toString();
        return lcsSpaceOptimized(s, reversed);
    }

    private static int lcsSpaceOptimized(String s1, String s2) {
        int n = s1.length();
        int[] prev = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            int[] curr = new int[n + 1];
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    curr[j] = 1 + prev[j - 1];
                } else {
                    curr[j] = Math.max(prev[j], curr[j - 1]);
                }
            }
            prev = curr;
        }

        return prev[n];
    }

    public static void main(String[] args) {
        // Test Case 1:
        // s = "bbbab" -> Expected: 4 ("bbbb")
        String s1 = "bbbab";
        System.out.println("Test 1 (\"bbbab\"): " + longestPalindromeSubseq(s1)); // 4

        // Test Case 2:
        // s = "cbbd" -> Expected: 2 ("bb")
        String s2 = "cbbd";
        System.out.println("Test 2 (\"cbbd\"): " + longestPalindromeSubseq(s2)); // 2
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
