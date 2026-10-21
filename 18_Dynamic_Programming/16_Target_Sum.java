/**
 * Problem Name: Target Sum
 * Platform: LeetCode (LC 494) / GeeksforGeeks
 * Difficulty: Medium
 *
 * Time Complexity: O(N * S1) where S1 = (totalSum + target) / 2
 * Space Complexity:
 *   - Memoization: O(N * S1) table + O(N) recursion stack
 *   - Tabulation: O(N * S1) table
 *   - Space-Optimized DP: O(S1) 1D array
 *
 * Approach:
 *   - We are given an integer array `nums` and an integer `target`.
 *   - We need to assign '+' or '-' signs to each element such that the total sum equals target.
 *   - Let P be the subset of elements with '+' signs and N be the subset with '-' signs:
 *       sum(P) - sum(N) = target
 *       sum(P) + sum(N) = totalSum
 *       ------------------------------------
 *       2 * sum(P) = totalSum + target  =>  sum(P) = (totalSum + target) / 2
 *   - Edge conditions:
 *     1. totalSum < abs(target): target is impossible to reach.
 *     2. (totalSum + target) must be non-negative and EVEN.
 *     If either check fails, return 0.
 *   - Thus, Target Sum reduces directly to counting the number of subsets with sum = (totalSum + target) / 2.
 */

import java.util.Arrays;

class TargetSum {

    public static int findTargetSumWays(int[] nums, int target) {
        int totalSum = 0;
        for (int x : nums) totalSum += x;

        // Validation checks
        if (totalSum < Math.abs(target) || (totalSum + target) % 2 != 0) {
            return 0;
        }

        int s1 = (totalSum + target) / 2;
        return countSubsetsWithSumK(nums, s1);
    }

    private static int countSubsetsWithSumK(int[] nums, int target) {
        int n = nums.length;
        int[] dp = new int[target + 1];

        // Base case at index 0
        if (nums[0] == 0) {
            dp[0] = 2; // +0 or -0
        } else {
            dp[0] = 1;
            if (nums[0] <= target) {
                dp[nums[0]] = 1;
            }
        }

        for (int i = 1; i < n; i++) {
            int[] next = new int[target + 1];
            for (int t = 0; t <= target; t++) {
                int notPick = dp[t];
                int pick = 0;
                if (nums[i] <= t) {
                    pick = dp[t - nums[i]];
                }
                next[t] = notPick + pick;
            }
            dp = next;
        }

        return dp[target];
    }

    public static void main(String[] args) {
        // Test Case 1:
        // nums = [1, 1, 1, 1, 1], target = 3 -> Expected: 5
        // totalSum = 5, s1 = (5 + 3) / 2 = 4. Subsets of size 4 ones: C(5, 4) = 5
        int[] nums1 = {1, 1, 1, 1, 1};
        int target1 = 3;
        System.out.println("Test 1 (nums=[1,1,1,1,1], target=3): " + findTargetSumWays(nums1, target1)); // 5

        // Test Case 2:
        // nums = [1], target = 1 -> Expected: 1 (+1)
        int[] nums2 = {1};
        int target2 = 1;
        System.out.println("Test 2 (nums=[1], target=1): " + findTargetSumWays(nums2, target2)); // 1

        // Test Case 3:
        // nums = [1], target = 2 -> Expected: 0
        int[] nums3 = {1};
        int target3 = 2;
        System.out.println("Test 3 (nums=[1], target=2): " + findTargetSumWays(nums3, target3)); // 0
    }
}
