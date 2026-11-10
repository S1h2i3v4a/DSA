/*
QUESTION:
Palindrome Partitioning II (Minimum Cuts).

APPROACH:
- Memoization: 1D array `dp[n]`.

TIME COMPLEXITY: O(N^2).
SPACE COMPLEXITY: O(N) table + O(N) stack.
*/

import java.util.Arrays;

class Solution {
    public int minCut(String s) {
        int n = s.length();
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return solve(0, s, dp) - 1;
    }

    private int solve(int i, String s, int[] dp) {
        if (i == s.length()) return 0;
        if (dp[i] != -1) return dp[i];

        int minCost = Integer.MAX_VALUE;
        for (int j = i; j < s.length(); j++) {
            if (isPalindrome(i, j, s)) {
                int cost = 1 + solve(j + 1, s, dp);
                minCost = Math.min(minCost, cost);
            }
        }

        return dp[i] = minCost;
    }

    private boolean isPalindrome(int i, int j, String s) {
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) return false;
        }
        return true;
    }
}
