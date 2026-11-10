/*
QUESTION:
Count all subsets of arr with sum equal to target.

APPROACH:
- Space-Optimized 1D Tabulation:
- Maintain 1D array of size `target + 1`, updated backwards for each element.

TIME COMPLEXITY: O(N * Target).
SPACE COMPLEXITY: O(Target) 1D array.
*/

class Solution {
    private static final int MOD = 1000000007;

    public int findWays(int[] arr, int target) {
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
