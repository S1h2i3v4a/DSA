/**
 * Problem Name: Partition Equal Subset Sum
 * Platform: LeetCode (LC 416) / GeeksforGeeks
 * Difficulty: Medium
 *
 * Time Complexity: O(N * (totalSum / 2))
 * Space Complexity:
 *   - Memoization: O(N * target) table + O(N) recursion stack
 *   - Tabulation: O(N * target) table
 *   - Space-Optimized DP: O(target) 1D boolean array where target = totalSum / 2
 *
 * Approach:
 *   - Can we partition array `nums` into two subsets S1 and S2 such that sum(S1) == sum(S2)?
 *   - If sum(nums) is ODD:
 *       An integer sum cannot be split into two equal integer halves; return false immediately.
 *   - If sum(nums) is EVEN:
 *       Target sum for one subset is target = sum(nums) / 2.
 *       If we find a subset with sum equal to target, the remaining elements automatically sum
 *       to the other target.
 *   - Thus, the problem reduces strictly to Subset Sum with target = sum(nums) / 2.
 *   - We provide:
 *     1. Top-Down DP with Memoization
 *     2. Bottom-Up DP with Tabulation
 *     3. Space-Optimized DP using an O(target) 1D array.
 */

import java.util.Arrays;

class PartitionEqualSubsetSum {

    // ==========================================
    // Approach 1: Top-Down DP (Memoization)
    // ==========================================
    public static boolean canPartitionMemo(int[] nums) {
        int totalSum = 0;
        for (int num : nums) totalSum += num;

        // Odd total sum cannot be partitioned into two equal integer halves
        if (totalSum % 2 != 0) return false;

        int target = totalSum / 2;
        int n = nums.length;
        int[][] dp = new int[n][target + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return memoHelper(n - 1, target, nums, dp);
    }

    private static boolean memoHelper(int index, int target, int[] nums, int[][] dp) {
        if (target == 0) return true;
        if (index == 0) return nums[0] == target;
        if (dp[index][target] != -1) return dp[index][target] == 1;

        boolean notPick = memoHelper(index - 1, target, nums, dp);
        boolean pick = false;
        if (nums[index] <= target) {
            pick = memoHelper(index - 1, target - nums[index], nums, dp);
        }

        boolean result = notPick || pick;
        dp[index][target] = result ? 1 : 0;
        return result;
    }

    // ==========================================
    // Approach 2: Bottom-Up DP (Tabulation)
    // ==========================================
    public static boolean canPartitionTabulation(int[] nums) {
        int totalSum = 0;
        for (int num : nums) totalSum += num;

        if (totalSum % 2 != 0) return false;

        int target = totalSum / 2;
        int n = nums.length;
        boolean[][] dp = new boolean[n][target + 1];

        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }

        if (nums[0] <= target) {
            dp[0][nums[0]] = true;
        }

        for (int i = 1; i < n; i++) {
            for (int t = 1; t <= target; t++) {
                boolean notPick = dp[i - 1][t];
                boolean pick = false;
                if (nums[i] <= t) {
                    pick = dp[i - 1][t - nums[i]];
                }
                dp[i][t] = notPick || pick;
            }
        }

        return dp[n - 1][target];
    }

    // ==========================================
    // Approach 3: Space-Optimized DP - O(target) Space
    // ==========================================
    public static boolean canPartitionSpaceOptimized(int[] nums) {
        int totalSum = 0;
        for (int num : nums) totalSum += num;

        if (totalSum % 2 != 0) return false;

        int target = totalSum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        if (nums[0] <= target) {
            dp[nums[0]] = true;
        }

        for (int i = 1; i < nums.length; i++) {
            boolean[] next = new boolean[target + 1];
            next[0] = true;
            for (int t = 1; t <= target; t++) {
                boolean notPick = dp[t];
                boolean pick = false;
                if (nums[i] <= t) {
                    pick = dp[t - nums[i]];
                }
                next[t] = notPick || pick;
            }
            dp = next;
        }

        return dp[target];
    }

    public static void main(String[] args) {
        // Test Case 1:
        // nums = [1, 5, 11, 5] -> Expected: true (Subsets: [1, 5, 5] and [11])
        int[] nums1 = {1, 5, 11, 5};
        System.out.println("Test 1 [1, 5, 11, 5]:");
        System.out.println("  Memoization:     " + canPartitionMemo(nums1)); // true
        System.out.println("  Tabulation:      " + canPartitionTabulation(nums1)); // true
        System.out.println("  Space-Optimized: " + canPartitionSpaceOptimized(nums1)); // true

        // Test Case 2:
        // nums = [1, 2, 3, 5] -> Expected: false (Total sum = 11 is odd)
        int[] nums2 = {1, 2, 3, 5};
        System.out.println("\nTest 2 [1, 2, 3, 5]:");
        System.out.println("  Memoization:     " + canPartitionMemo(nums2)); // false
        System.out.println("  Tabulation:      " + canPartitionTabulation(nums2)); // false
        System.out.println("  Space-Optimized: " + canPartitionSpaceOptimized(nums2)); // false
    }
}
