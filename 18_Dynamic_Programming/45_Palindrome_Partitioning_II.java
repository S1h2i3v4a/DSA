/**
 * Problem Name: Palindrome Partitioning II (Minimum Cuts)
 * Platform: LeetCode (LC 132) / GeeksforGeeks
 * Difficulty: Hard
 *
 * Time Complexity: O(N^2) where N is string length
 * Space Complexity: O(N^2) for precomputed palindrome table + O(N) for 1D DP array
 *
 * Approach:
 *   - Return the minimum cuts needed for a palindrome partitioning of string s.
 *   - Front-Partitioning DP Pattern:
 *     Let f(i) be the minimum cuts required for substring s[i..n-1]:
 *     f(i) = min_{i <= j < n, isPal[i][j] == true} (1 + f(j + 1)).
 *     Base Case: f(n) = 0.
 *     Result: f(0) - 1 (since k substrings require k - 1 cuts).
 *   - To achieve O(N^2) time, precompute isPal[i][j] in O(N^2) using 2D DP.
 */

import java.util.Arrays;

class PalindromePartitioningII {

    public static int minCut(String s) {
        int n = s.length();
        if (n <= 1) return 0;

        // Step 1: Precompute palindrome lookup table
        boolean[][] isPal = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i <= 2 || isPal[i + 1][j - 1]) {
                        isPal[i][j] = true;
                    }
                }
            }
        }

        // Step 2: 1D DP for minimum cuts
        int[] dp = new int[n + 1];
        dp[n] = 0;

        for (int i = n - 1; i >= 0; i--) {
            int minCost = Integer.MAX_VALUE;
            for (int j = i; j < n; j++) {
                if (isPal[i][j]) {
                    int cost = 1 + dp[j + 1];
                    minCost = Math.min(minCost, cost);
                }
            }
            dp[i] = minCost;
        }

        return dp[0] - 1;
    }

    public static void main(String[] args) {
        // Test Case 1: s = "aab" -> Expected: 1 (["aa", "b"])
        System.out.println("Test 1 (\"aab\"): " + minCut("aab")); // 1

        // Test Case 2: s = "a" -> Expected: 0
        System.out.println("Test 2 (\"a\"): " + minCut("a")); // 0

        // Test Case 3: s = "ab" -> Expected: 1
        System.out.println("Test 3 (\"ab\"): " + minCut("ab")); // 1
    }
}
