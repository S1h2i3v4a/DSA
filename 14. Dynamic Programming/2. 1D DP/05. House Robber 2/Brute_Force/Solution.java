/*
QUESTION:
Houses are arranged in a circle. First and last houses are adjacent. Return maximum amount possible to rob.

APPROACH:
- Since houses 0 and n-1 cannot both be robbed:
  Case 1: Rob houses from index 0 to n-2.
  Case 2: Rob houses from index 1 to n-1.
- Pure recursive branch for both cases and return max.

TIME COMPLEXITY: O(2^N) exponential recursion.
SPACE COMPLEXITY: O(N) recursion stack.
*/

class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];
        return Math.max(solve(nums, 0, n - 2), solve(nums, 1, n - 1));
    }

    private int solve(int[] nums, int start, int end) {
        return helper(nums, end, start);
    }

    private int helper(int[] nums, int i, int start) {
        if (i < start) return 0;
        if (i == start) return nums[start];
        int pick = nums[i] + helper(nums, i - 2, start);
        int notPick = helper(nums, i - 1, start);
        return Math.max(pick, notPick);
    }
}
