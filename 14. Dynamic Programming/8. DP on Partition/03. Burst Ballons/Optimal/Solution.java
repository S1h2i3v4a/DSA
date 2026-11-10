/*
QUESTION:
Burst Balloons.

APPROACH:
- Bottom-Up Tabulation:
- `dp[i][j] = max_{i <= k <= j}(arr[i-1] * arr[k] * arr[j+1] + dp[i][k-1] + dp[k+1][j])`.

TIME COMPLEXITY: O(N^3).
SPACE COMPLEXITY: O(N^2) dp table.
*/

class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n + 2];
        arr[0] = 1;
        arr[n + 1] = 1;
        System.arraycopy(nums, 0, arr, 1, n);

        int[][] dp = new int[n + 2][n + 2];

        for (int i = n; i >= 1; i--) {
            for (int j = i; j <= n; j++) {
                int maxi = Integer.MIN_VALUE;
                for (int k = i; k <= j; k++) {
                    int coins = arr[i - 1] * arr[k] * arr[j + 1] + dp[i][k - 1] + dp[k + 1][j];
                    maxi = Math.max(maxi, coins);
                }
                dp[i][j] = maxi;
            }
        }

        return dp[1][n];
    }
}
