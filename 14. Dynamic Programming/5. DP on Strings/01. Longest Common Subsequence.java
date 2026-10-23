/*
QUESTION:
Given two strings 'text1' and 'text2', return the length of their longest 
common subsequence. If there is no common subsequence, return 0.

A subsequence of a string is a new string generated from the original 
string with some characters (can be none) deleted without changing the 
relative order of the remaining characters.

Example:
Input: text1 = "abcde", text2 = "ace"
Output: 3
Explanation: The longest common subsequence is "ace" and its length is 3.

APPROACH:
- This problem can be solved using Dynamic Programming (DP).
- Initialize a 2D DP array 'dp' where dp[i][j] represents the length of 
  the longest common subsequence between the prefixes text1[0..i] and 
  text2[0..j].
- Base case: dp[i][0] = 0 (empty second string) and dp[0][j] = 0 
  (empty first string).
- For each character at index 'i' in text1 and each character at index 
  'j' in text2:
  - If text1[i] == text2[j], then dp[i][j] = dp[i-1][j-1] + 1
  - Else, dp[i][j] = max(dp[i-1][j], dp[i][j-1])
- The required answer will be dp[n-1][m-1] where 'n' is the length of 
  text1 and 'm' is the length of text2.

COMPLEXITY ANALYSIS:
- The time complexity of the solution is O(n*m), where 'n' and 'm' are 
  the lengths of text1 and text2 respectively.
- The space complexity is O(n*m) for the dp array.

CODE:
*/

import java.io.*;
import java.util.*;
import java.util.Arrays;

// CODE:-
class LongestCommonSubsequence {

    // ==========================================
    // Approach 1: Top-Down DP (Memoization)
    // ==========================================
    public static int longestCommonSubsequenceMemo(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return memoHelper(m, n, text1, text2, dp);
    }

    private static int memoHelper(int i, int j, String s1, String s2, int[][] dp) {
        if (i == 0 || j == 0) {
            return 0;
        }

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
            return dp[i][j] = 1 + memoHelper(i - 1, j - 1, s1, s2, dp);
        } else {
            return dp[i][j] = Math.max(
                memoHelper(i - 1, j, s1, s2, dp),
                memoHelper(i, j - 1, s1, s2, dp)
            );
        }
    }

    // ==========================================
    // Approach 2: Bottom-Up DP (Tabulation)
    // ==========================================
    public static int longestCommonSubsequenceTabulation(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];

        // Base cases: dp[0][j] = 0 and dp[i][0] = 0 are already initialized to 0

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }

    // ==========================================
    // Approach 3: Space-Optimized DP - O(N) Space
    // ==========================================
    public static int longestCommonSubsequenceSpaceOptimized(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[] prev = new int[n + 1];

        for (int i = 1; i <= m; i++) {
            int[] curr = new int[n + 1];
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
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
        // text1 = "abcde", text2 = "ace" -> Expected: 3 ("ace")
        String s1 = "abcde", s2 = "ace";
        System.out.println("Test 1 (\"abcde\", \"ace\"):");
        System.out.println("  Memoization:     " + longestCommonSubsequenceMemo(s1, s2)); // 3
        System.out.println("  Tabulation:      " + longestCommonSubsequenceTabulation(s1, s2)); // 3
        System.out.println("  Space-Optimized: " + longestCommonSubsequenceSpaceOptimized(s1, s2)); // 3

        // Test Case 2:
        // text1 = "abc", text2 = "abc" -> Expected: 3 ("abc")
        String s3 = "abc", s4 = "abc";
        System.out.println("\nTest 2 (\"abc\", \"abc\"):");
        System.out.println("  Memoization:     " + longestCommonSubsequenceMemo(s3, s4)); // 3
        System.out.println("  Tabulation:      " + longestCommonSubsequenceTabulation(s3, s4)); // 3
        System.out.println("  Space-Optimized: " + longestCommonSubsequenceSpaceOptimized(s3, s4)); // 3

        // Test Case 3:
        // text1 = "abc", text2 = "def" -> Expected: 0
        String s5 = "abc", s6 = "def";
        System.out.println("\nTest 3 (\"abc\", \"def\"):");
        System.out.println("  Space-Optimized: " + longestCommonSubsequenceSpaceOptimized(s5, s6)); // 0
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
