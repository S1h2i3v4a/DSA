/*
QUESTION:
Assign '+' and '-' to each integer in nums to make sum equal to target.

APPROACH:
- Memoization with HashMap or 2D array offset by totalSum.
- Reduce to Subset Sum: P - N = target, P + N = sum => P = (sum + target) / 2.

TIME COMPLEXITY: O(N * S).
SPACE COMPLEXITY: O(N * S) memo table + O(N) stack.
*/

import java.util.Arrays;

class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int total = 0;
        for (int x : nums) total += x;
        if (total - target < 0 || (total - target) % 2 != 0) return 0;
        int s2 = (total - target) / 2;

        int[][] dp = new int[nums.length][s2 + 1];
        for (int[] row : dp) Arrays.fill(row, -1);
        return solve(nums.length - 1, s2, nums, dp);
    }

    private int solve(int i, int s, int[] nums, int[][] dp) {
        if (i == 0) {
            if (s == 0 && nums[0] == 0) return 2;
            if (s == 0 || s == nums[0]) return 1;
            return 0;
        }
        if (dp[i][s] != -1) return dp[i][s];

        int notPick = solve(i - 1, s, nums, dp);
        int pick = 0;
        if (nums[i] <= s) pick = solve(i - 1, s - nums[i], nums, dp);

        return dp[i][s] = notPick + pick;
    }
}
