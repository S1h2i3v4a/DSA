/*
QUESTION:
Given a rod of length n and price[] array, determine max value obtainable.

APPROACH:
- Bottom-Up Tabulation:
- `dp[len] = max_{0 <= i < len}(price[i] + dp[len - i - 1])`.

TIME COMPLEXITY: O(N^2).
SPACE COMPLEXITY: O(N) 1D array.
*/

class Solution {
    public int cutRod(int[] price, int n) {
        int[] dp = new int[n + 1];

        for (int len = 1; len <= n; len++) {
            int maxVal = Integer.MIN_VALUE;
            for (int i = 0; i < len; i++) {
                maxVal = Math.max(maxVal, price[i] + dp[len - i - 1]);
            }
            dp[len] = maxVal;
        }

        return dp[n];
    }
}
