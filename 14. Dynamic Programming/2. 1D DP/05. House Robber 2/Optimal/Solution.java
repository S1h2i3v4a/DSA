/*
QUESTION:
Houses are arranged in a circle. First and last houses are adjacent. Return maximum amount possible to rob.

APPROACH:
- Space-Optimized Tabulation:
- Run the linear House Robber 1 logic (O(1) space) on [0, n-2] and [1, n-1].
- Result is max of both results.

TIME COMPLEXITY: O(N) two linear passes.
SPACE COMPLEXITY: O(1) constant auxiliary space.
*/

class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];
        return Math.max(robLinear(nums, 0, n - 2), robLinear(nums, 1, n - 1));
    }

    private int robLinear(int[] nums, int start, int end) {
        int prev2 = 0;
        int prev = 0;
        for (int i = start; i <= end; i++) {
            int cur = Math.max(prev, prev2 + nums[i]);
            prev2 = prev;
            prev = cur;
        }
        return prev;
    }
}
