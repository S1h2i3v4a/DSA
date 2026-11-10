/*
QUESTION:
Given a rod of length n and price[] array, determine max value obtainable.

APPROACH:
- Memoization: 1D array `dp` of size `n + 1`.

TIME COMPLEXITY: O(N^2).
SPACE COMPLEXITY: O(N) dp array + O(N) stack.
*/

import java.util.Arrays;

class Solution {
    public int cutRod(int[] price, int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return solve(price, n, dp);
    }

    private int solve(int[] price, int n, int[] dp) {
        if (n <= 0) return 0;
        if (dp[n] != -1) return dp[n];
        int maxVal = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            maxVal = Math.max(maxVal, price[i] + solve(price, n - i - 1, dp));
        }
        return dp[n] = maxVal;
    }
}
