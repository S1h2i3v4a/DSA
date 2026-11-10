/*
QUESTION:
Partition Array for Maximum Sum.

APPROACH:
- Bottom-Up Tabulation:
- `dp[i]` represents max sum starting from index `i` to end.
- `dp[i] = max_{i <= j < min(n, i+k)}(maxVal * (j - i + 1) + dp[j + 1])`.

TIME COMPLEXITY: O(N * K).
SPACE COMPLEXITY: O(N) dp array.
*/

class Solution {
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;
        int[] dp = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            int maxVal = 0;
            int maxSum = 0;
            int limit = Math.min(n, i + k);

            for (int j = i; j < limit; j++) {
                maxVal = Math.max(maxVal, arr[j]);
                int sum = maxVal * (j - i + 1) + dp[j + 1];
                maxSum = Math.max(maxSum, sum);
            }
            dp[i] = maxSum;
        }

        return dp[0];
    }
}
