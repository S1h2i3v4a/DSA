/*
QUESTION:
Given an integer array nums, return true if you can partition the array into two subsets such that the sum of elements in both subsets is equal.

APPROACH:
- If total sum is odd, cannot partition into equal subsets.
- Else find if subset with sum = totalSum / 2 exists using recursion.

TIME COMPLEXITY: O(2^N).
SPACE COMPLEXITY: O(N) recursion stack.
*/

class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int x : nums) sum += x;
        if (sum % 2 != 0) return false;
        return solve(nums.length - 1, sum / 2, nums);
    }

    private boolean solve(int idx, int target, int[] nums) {
        if (target == 0) return true;
        if (idx == 0) return nums[0] == target;
        boolean notPick = solve(idx - 1, target, nums);
        boolean pick = false;
        if (nums[idx] <= target) pick = solve(idx - 1, target - nums[idx], nums);
        return notPick || pick;
    }
}
