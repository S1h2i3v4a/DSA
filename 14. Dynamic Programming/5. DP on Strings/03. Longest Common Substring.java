/*
QUESTION:
Given two strings. The task is to find the length of the longest common substring.

Example:
Input: S1 = "ABCDGH", S2 = "ACDGHR", n = 6, m = 6
Output: 4
Explanation: The longest common substring is "CDGH" which has length 4.

APPROACH:
- This problem can be solved using Dynamic Programming (DP).
- Initialize a 2D DP array 'dp' where dp[i][j] represents the length of 
  the longest common suffix of substrings S1[0..i-1] and S2[0..j-1].
- Initialize a variable 'maxi' to keep track of the maximum length found.
- For each character at index 'i' in S1 and each character at index 'j' 
  in S2:
  - If S1[i] == S2[j], then dp[i][j] = dp[i-1][j-1] + 1
  - Else, dp[i][j] = 0 (as no common suffix found)
  - Update 'maxi' with dp[i][j]
- The required answer will be 'maxi'.

COMPLEXITY ANALYSIS:
- The time complexity of the solution is O(n*m), where 'n' and 'm' are 
  the lengths of S1 and S2 respectively.
- The space complexity is O(n*m) for the dp array.

CODE:
*/

import java.io.*;
import java.util.*;

// CODE:-
class LongestCommonSubstring {

    // ==========================================
    // Approach 1: Bottom-Up DP (Tabulation)
    // ==========================================
    public static int longestCommonSubstrTabulation(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        int maxLen = 0;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    maxLen = Math.max(maxLen, dp[i][j]);
                } else {
                    dp[i][j] = 0; // Continuity broken
                }
            }
        }

        return maxLen;
    }

    // ==========================================
    // Approach 2: Space-Optimized DP - O(N) Space
    // ==========================================
    public static int longestCommonSubstrSpaceOptimized(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[] prev = new int[n + 1];
        int maxLen = 0;

        for (int i = 1; i <= m; i++) {
            int[] curr = new int[n + 1];
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    curr[j] = 1 + prev[j - 1];
                    maxLen = Math.max(maxLen, curr[j]);
                } else {
                    curr[j] = 0;
                }
            }
            prev = curr;
        }

        return maxLen;
    }

    public static void main(String[] args) {
        // Test Case 1:
        // s1 = "ABCDGH", s2 = "ACDGHR" -> Expected: 4 ("CDGH")
        String s1 = "ABCDGH", s2 = "ACDGHR";
        System.out.println("Test 1 (\"ABCDGH\", \"ACDGHR\"):");
        System.out.println("  Tabulation:      " + longestCommonSubstrTabulation(s1, s2)); // 4
        System.out.println("  Space-Optimized: " + longestCommonSubstrSpaceOptimized(s1, s2)); // 4

        // Test Case 2:
        // s1 = "ABC", s2 = "ACB" -> Expected: 1
        String s3 = "ABC", s4 = "ACB";
        System.out.println("\nTest 2 (\"ABC\", \"ACB\"):");
        System.out.println("  Tabulation:      " + longestCommonSubstrTabulation(s3, s4)); // 1
        System.out.println("  Space-Optimized: " + longestCommonSubstrSpaceOptimized(s3, s4)); // 1
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
