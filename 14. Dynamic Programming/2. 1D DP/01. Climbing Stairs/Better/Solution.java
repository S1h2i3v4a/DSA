/*
QUESTION:
You are climbing a staircase. It takes n steps to reach the top.
Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

APPROACH:
- Memoization (Top-down DP):
- Cache results of distinct steps in an integer array `dp` of size n+1.

TIME COMPLEXITY: O(N) each state computed once.
SPACE COMPLEXITY: O(N) dp array + recursion stack.
*/

import java.util.Arrays;

class Solution {
    public int climbStairs(int n) {
        if (n <= 2) return n;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return solve(n, dp);
    }

    private int solve(int n, int[] dp) {
        if (n <= 2) return n;
        if (dp[n] != -1) return dp[n];
        return dp[n] = solve(n - 1, dp) + solve(n - 2, dp);
    }
}
