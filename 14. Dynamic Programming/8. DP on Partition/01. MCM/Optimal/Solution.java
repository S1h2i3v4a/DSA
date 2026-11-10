/*
QUESTION:
Matrix Chain Multiplication (MCM).

APPROACH:
- Bottom-Up Tabulation:
- Fill `dp[i][j]` by chain length `len` from 2 to n-1.

TIME COMPLEXITY: O(N^3).
SPACE COMPLEXITY: O(N^2) dp table.
*/

class Solution {
    public int matrixMultiplication(int[] arr, int n) {
        int[][] dp = new int[n][n];

        for (int i = n - 1; i >= 1; i--) {
            for (int j = i + 1; j < n; j++) {
                int mini = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int cost = dp[i][k] + dp[k + 1][j] + arr[i - 1] * arr[k] * arr[j];
                    mini = Math.min(mini, cost);
                }
                dp[i][j] = mini;
            }
        }

        return dp[1][n - 1];
    }
}
