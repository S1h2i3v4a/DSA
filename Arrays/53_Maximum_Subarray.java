/*
 * Problem Name: Kadane's Algorithm, Maximum Subarray Sum
 * Platform: LeetCode (53) / GeeksforGeeks
 * Difficulty: Medium
 * Time Complexity: O(N)
 * Space Complexity: O(1)
 */

class Solution {
    public int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int currentSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }
}
