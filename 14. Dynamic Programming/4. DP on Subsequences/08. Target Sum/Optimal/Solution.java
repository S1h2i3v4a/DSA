/*
QUESTION:
Assign '+' and '-' to each integer in nums to make sum equal to target.

APPROACH:
- Space-Optimized 1D Tabulation:
- Equivalent to finding subsets summing to (sum - target) / 2.
- 1D array of size (sum - target) / 2 + 1.

TIME COMPLEXITY: O(N * S).
SPACE COMPLEXITY: O(S) where S = (sum - target) / 2.
*/

class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int total = 0;
        for (int x : nums) total += x;
        if (total - target < 0 || (total - target) % 2 != 0) return 0;
        int s2 = (total - target) / 2;

        int[] dp = new int[s2 + 1];
        dp[0] = 1;

        for (int num : nums) {
            for (int s = s2; s >= num; s--) {
                dp[s] += dp[s - num];
            }
        }

        return dp[s2];
    }
}
