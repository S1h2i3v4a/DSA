/*
QUESTION:
Given an array `nums` of house values, return the maximum amount of money you can rob without robbing two adjacent houses.

APPROACH:
- Recursion: at index i, choose to:
  1. Pick house i: gain nums[i] + solve(i - 2)
  2. Not pick house i: gain solve(i - 1)
- Return max(pick, notPick).

TIME COMPLEXITY: O(2^N) exponential recursion tree.
SPACE COMPLEXITY: O(N) recursion stack.
*/

class Solution {
    public int rob(int[] nums) {
        return solve(nums.length - 1, nums);
    }

    private int solve(int i, int[] nums) {
        if (i < 0) return 0;
        if (i == 0) return nums[0];
        int pick = nums[i] + solve(i - 2, nums);
        int notPick = solve(i - 1, nums);
        return Math.max(pick, notPick);
    }
}
