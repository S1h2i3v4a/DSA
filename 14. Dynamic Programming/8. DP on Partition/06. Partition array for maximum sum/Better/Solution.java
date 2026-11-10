/*
QUESTION:
Partition Array for Maximum Sum.

APPROACH:
- Memoization: 1D array `dp[n]`.

TIME COMPLEXITY: O(N * K).
SPACE COMPLEXITY: O(N) table + O(N) stack.
*/

import java.util.Arrays;

class Solution {
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return solve(0, arr, k, dp);
    }

    private int solve(int i, int[] arr, int k, int[] dp) {
        if (i == arr.length) return 0;
        if (dp[i] != -1) return dp[i];

        int maxVal = 0;
        int maxSum = 0;
        int limit = Math.min(arr.length, i + k);

        for (int j = i; j < limit; j++) {
            maxVal = Math.max(maxVal, arr[j]);
            int sum = maxVal * (j - i + 1) + solve(j + 1, arr, k, dp);
            maxSum = Math.max(maxSum, sum);
        }

        return dp[i] = maxSum;
    }
}
