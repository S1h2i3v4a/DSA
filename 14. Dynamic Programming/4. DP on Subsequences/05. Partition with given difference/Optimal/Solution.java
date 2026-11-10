/*
QUESTION:
Partition array into two subsets with given difference d.

APPROACH:
- Space-Optimized 1D Tabulation for target = (totalSum - d) / 2.

TIME COMPLEXITY: O(N * Target).
SPACE COMPLEXITY: O(Target) 1D array.
*/

class Solution {
    private static final int MOD = 1000000007;

    public int countPartitions(int n, int d, int[] arr) {
        int total = 0;
        for (int x : arr) total += x;
        if (total - d < 0 || (total - d) % 2 != 0) return 0;
        int target = (total - d) / 2;

        int[] dp = new int[target + 1];
        dp[0] = 1;

        for (int num : arr) {
            for (int t = target; t >= num; t--) {
                dp[t] = (dp[t] + dp[t - num]) % MOD;
            }
        }

        return dp[target];
    }
}
