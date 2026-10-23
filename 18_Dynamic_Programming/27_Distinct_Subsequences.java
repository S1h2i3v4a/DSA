/**
 * Problem Name: Distinct Subsequences
 * Platform: LeetCode (LC 115) / GeeksforGeeks
 * Difficulty: Hard
 *
 * Time Complexity: O(M * N) where M = s.length(), N = t.length()
 * Space Complexity:
 *   - Memoization: O(M * N) table + O(M + N) recursion stack
 *   - Tabulation: O(M * N) table
 *   - Space-Optimized DP: O(N) 1D array
 *
 * Approach:
 *   - Given two strings `s` and `t`, find the number of distinct subsequences of `s` that equal `t`.
 *   - Recurrence:
 *     At s[i - 1] and t[j - 1]:
 *       If s[i - 1] == t[j - 1]:
 *         We have two choices:
 *           1. Match current character: f(i - 1, j - 1)
 *           2. Skip current character in s to look for other matches: f(i - 1, j)
 *         Total = f(i - 1, j - 1) + f(i - 1, j)
 *       Else:
 *         Cannot match: f(i - 1, j)
 *     Base Cases:
 *       - If j == 0: return 1 (an empty string t is formed by 1 empty subsequence).
 *       - If i == 0: return 0 (string s is exhausted while t is non-empty).
 *   - Space-Optimized DP:
 *     By traversing `j` backwards from N down to 1, a single 1D array of size N + 1
 *     preserves the un-overwritten states from row i - 1.
 */

import java.util.Arrays;

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
