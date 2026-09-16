/*
 * Problem Name: Partition Equal Subset Sum
 * Platform: LeetCode (416) / GeeksforGeeks
 * Difficulty: Medium
 * Time Complexity: O(N * Sum)
 * Space Complexity: O(N * Sum)
 */

class Solution {
    public boolean canPartition(int[] nums) {
        int totalSum = 0;
        for (int num : nums) totalSum += num;
        if (totalSum % 2 != 0) return false;

        int target = totalSum / 2;
        Boolean[][] memo = new Boolean[nums.length][target + 1];
        return isSubsetSum(nums, 0, target, memo);
    }

    private boolean isSubsetSum(int[] nums, int index, int target, Boolean[][] memo) {
        if (target == 0) return true;
        if (index >= nums.length || target < 0) return false;
        if (memo[index][target] != null) return memo[index][target];

        boolean include = isSubsetSum(nums, index + 1, target - nums[index], memo);
        boolean exclude = isSubsetSum(nums, index + 1, target, memo);

        return memo[index][target] = include || exclude;
    }
}
