/*
QUESTION:
Find minimum jumps to reach end using 1D Dynamic Programming.

APPROACH:
- `dp[i]` stores the minimum jumps required to reach `n - 1` from index `i`.
- Initialize `dp[n - 1] = 0`, all other `dp[i] = infinity`.
- For `i` from `n - 2` down to `0`:
  - For `step` from `1` to `nums[i]`: `dp[i] = min(dp[i], 1 + dp[i + step])`.

TIME COMPLEXITY: O(N^2) nested loops.
SPACE COMPLEXITY: O(N) for DP array.
*/

import java.util.Arrays;

class Solution {
    public int jump(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 100000);
        dp[n - 1] = 0;

        for (int i = n - 2; i >= 0; i--) {
            for (int step = 1; step <= nums[i] && i + step < n; step++) {
                dp[i] = Math.min(dp[i], 1 + dp[i + step]);
            }
        }
        return dp[0];
    }
}
