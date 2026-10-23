/*
QUESTION:
Given a string s, partition s such that every substring of the partition is a palindrome.
Return the minimum cuts needed for a palindrome partitioning of s.

Example:
Input: s = "aab"
Output: 1
Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.

APPROACH:
- This problem can be solved using dynamic programming.
- Define a 1D DP array `dp` where dp[i] represents the minimum cuts needed for a palindrome partitioning of s starting from index i.
- Iterate through each index i of the string.
- For each index i, iterate through all possible ending indices j such that j is in the range [i, n).
- Check if the substring from index i to j is a palindrome. If yes, update the dp[i] value by taking the minimum of its current value and dp[j + 1] + 1.
- Finally, return dp[0] - 1, which represents the minimum cuts needed for a palindrome partitioning of the entire string.

COMPLEXITY ANALYSIS:
- The time complexity of this approach is O(n^2), where n is the length of the string.
- The space complexity is O(n) due to the dp array.
- Overall, the algorithm runs in O(n^2) time and O(n) space.

CODE:
*/

import java.io.*;
import java.util.*;
import java.util.Arrays;

// CODE:-
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
