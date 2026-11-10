/*
QUESTION:
Partition array into two subsets with given difference d.

APPROACH:
- Memoized subset sum counting target = (totalSum - d) / 2.

TIME COMPLEXITY: O(N * Target).
SPACE COMPLEXITY: O(N * Target) + O(N) stack.
*/

import java.util.Arrays;

class Solution {
    private static final int MOD = 1000000007;

    public int countPartitions(int n, int d, int[] arr) {
        int total = 0;
        for (int x : arr) total += x;
        if (total - d < 0 || (total - d) % 2 != 0) return 0;
        int target = (total - d) / 2;

        int[][] dp = new int[n][target + 1];
        for (int[] row : dp) Arrays.fill(row, -1);
        return solve(n - 1, target, arr, dp);
    }

    private int solve(int i, int target, int[] arr, int[][] dp) {
        if (i == 0) {
            if (target == 0 && arr[0] == 0) return 2;
            if (target == 0 || target == arr[0]) return 1;
            return 0;
        }
        if (dp[i][target] != -1) return dp[i][target];

        int notPick = solve(i - 1, target, arr, dp);
        int pick = 0;
        if (arr[i] <= target) pick = solve(i - 1, target - arr[i], arr, dp);

        return dp[i][target] = (notPick + pick) % MOD;
    }
}
