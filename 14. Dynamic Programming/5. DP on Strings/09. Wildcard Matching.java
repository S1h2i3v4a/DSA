/*
QUESTION:
Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:
- '?' Matches any single character.
- '*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).

APPROACH:
- This problem can be solved using dynamic programming.
- Let dp[i][j] represent whether the substring of s up to index i can be matched with the substring of p up to index j.
- If s[i] equals p[j] or p[j] is '?', the characters match and we move diagonally to check the next characters.
- If p[j] is '*', we have two options:
  1. Move vertically up (skip the '*').
  2. Move horizontally left (count '*' as matching zero characters).
- Base cases: When both i and j are -1, return true. When only j is -1, check if the remaining characters in p are all '*'.
- Return dp[n-1][m-1], where n is the length of s and m is the length of p.

COMPLEXITY ANALYSIS:
- The time complexity of this approach is O(n * m), where n is the length of string s and m is the length of string p.
- The space complexity is O(n * m) due to the dp array.
- Overall, the algorithm runs in O(n * m) time and O(n * m) space.

CODE:
*/

import java.io.*;
import java.util.*;
import java.util.Arrays;

// CODE:-
class WildcardMatching {

    // ==========================================
    // Approach 1: Top-Down DP (Memoization)
    // ==========================================
    public static boolean isMatchMemo(String s, String p) {
        int m = s.length();
        int n = p.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return memoHelper(m, n, s, p, dp);
    }

    private static boolean memoHelper(int i, int j, String s, String p, int[][] dp) {
        if (i == 0 && j == 0) return true;
        if (j == 0 && i > 0) return false;
        if (i == 0 && j > 0) {
            for (int k = 1; k <= j; k++) {
                if (p.charAt(k - 1) != '*') return false;
            }
            return true;
        }

        if (dp[i][j] != -1) return dp[i][j] == 1;

        boolean result = false;
        if (p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '?') {
            result = memoHelper(i - 1, j - 1, s, p, dp);
        } else if (p.charAt(j - 1) == '*') {
            result = memoHelper(i - 1, j, s, p, dp) || memoHelper(i, j - 1, s, p, dp);
        }

        dp[i][j] = result ? 1 : 0;
        return result;
    }

    // ==========================================
    // Approach 2: Bottom-Up DP (Tabulation)
    // ==========================================
    public static boolean isMatchTabulation(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];

        // Base cases
        dp[0][0] = true;
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 1];
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
            }
        }

        return dp[m][n];
    }

    // ==========================================
    // Approach 3: Space-Optimized DP - O(N) Space
    // ==========================================
    public static boolean isMatchSpaceOptimized(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[] prev = new boolean[n + 1];

        prev[0] = true;
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                prev[j] = prev[j - 1];
            }
        }

        for (int i = 1; i <= m; i++) {
            boolean[] curr = new boolean[n + 1];
            curr[0] = false; // Non-empty s matching empty p is always false
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '?') {
                    curr[j] = prev[j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    curr[j] = prev[j] || curr[j - 1];
                }
            }
            prev = curr;
        }

        return prev[n];
    }

    public static void main(String[] args) {
        // Test Case 1: s = "aa", p = "a" -> Expected: false
        System.out.println("Test 1 (\"aa\", \"a\"): " + isMatchSpaceOptimized("aa", "a")); // false

        // Test Case 2: s = "aa", p = "*" -> Expected: true
        System.out.println("Test 2 (\"aa\", \"*\"): " + isMatchSpaceOptimized("aa", "*")); // true

        // Test Case 3: s = "cb", p = "?a" -> Expected: false
        System.out.println("Test 3 (\"cb\", \"?a\"): " + isMatchSpaceOptimized("cb", "?a")); // false

        // Test Case 4: s = "adceb", p = "*a*b" -> Expected: true
        System.out.println("Test 4 (\"adceb\", \"*a*b\"): " + isMatchSpaceOptimized("adceb", "*a*b")); // true
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
