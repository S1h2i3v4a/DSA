/*
QUESTION:
Given two strings s and t, return the number of distinct subsequences of s which equals t.

APPROACH:
- The problem can be solved using dynamic programming.
- Let dp[i][j] represent the number of distinct subsequences of the first i characters of string s that match the first j characters of string t.
- If s[i] equals t[j], we have two choices: we can either include the current character (s[i]) in the subsequence, in which case we add dp[i-1][j-1] to our count, or we can exclude it and move on, so we add dp[i-1][j] to our count.
- If s[i] doesn't match t[j], we have no choice but to exclude s[i], so we add dp[i-1][j] to our count.
- Base cases: When j equals -1 (meaning we've matched all characters of t), return 1. When i equals -1 (meaning we've exhausted all characters of s), return 0.
- Return dp[n-1][m-1], where n is the length of s and m is the length of t.

COMPLEXITY ANALYSIS:
- The time complexity of this approach is O(n * m), where n is the length of string s and m is the length of string t.
- The space complexity is O(n * m) due to the dp array.
- Overall, the algorithm runs in O(n * m) time and O(n * m) space.

CODE:
*/

import java.io.*;
import java.util.*;
import java.util.Arrays;

// CODE:-
class DistinctSubsequences {

    // ==========================================
    // Approach 1: Top-Down DP (Memoization)
    // ==========================================
    public static int numDistinctMemo(String s, String t) {
        int m = s.length();
        int n = t.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return memoHelper(m, n, s, t, dp);
    }

    private static int memoHelper(int i, int j, String s, String t, int[][] dp) {
        if (j == 0) return 1; // Successfully formed t
        if (i == 0) return 0; // s exhausted
        if (dp[i][j] != -1) return dp[i][j];

        if (s.charAt(i - 1) == t.charAt(j - 1)) {
            return dp[i][j] = memoHelper(i - 1, j - 1, s, t, dp) + memoHelper(i - 1, j, s, t, dp);
        } else {
            return dp[i][j] = memoHelper(i - 1, j, s, t, dp);
        }
    }

    // ==========================================
    // Approach 2: Bottom-Up DP (Tabulation)
    // ==========================================
    public static int numDistinctTabulation(String s, String t) {
        int m = s.length();
        int n = t.length();
        int[][] dp = new int[m + 1][n + 1];

        // Base case: empty t has 1 match
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[m][n];
    }

    // ==========================================
    // Approach 3: Space-Optimized DP - O(N) Space
    // ==========================================
    public static int numDistinctSpaceOptimized(String s, String t) {
        int m = s.length();
        int n = t.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;

        for (int i = 1; i <= m; i++) {
            // Traverse backwards to avoid overwriting previous state
            for (int j = n; j >= 1; j--) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[j] = dp[j - 1] + dp[j];
                }
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        // Test Case 1:
        // s = "rabbbit", t = "rabbit" -> Expected: 3
        String s1 = "rabbbit", t1 = "rabbit";
        System.out.println("Test 1 (\"rabbbit\", \"rabbit\"):");
        System.out.println("  Memoization:     " + numDistinctMemo(s1, t1)); // 3
        System.out.println("  Tabulation:      " + numDistinctTabulation(s1, t1)); // 3
        System.out.println("  Space-Optimized: " + numDistinctSpaceOptimized(s1, t1)); // 3

        // Test Case 2:
        // s = "babgbag", t = "bag" -> Expected: 5
        String s2 = "babgbag", t2 = "bag";
        System.out.println("\nTest 2 (\"babgbag\", \"bag\"):");
        System.out.println("  Memoization:     " + numDistinctMemo(s2, t2)); // 5
        System.out.println("  Tabulation:      " + numDistinctTabulation(s2, t2)); // 5
        System.out.println("  Space-Optimized: " + numDistinctSpaceOptimized(s2, t2)); // 5
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
