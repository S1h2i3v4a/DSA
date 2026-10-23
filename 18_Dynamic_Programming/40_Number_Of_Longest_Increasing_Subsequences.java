/**
 * Problem Name: Number of Longest Increasing Subsequences
 * Platform: LeetCode (LC 673) / GeeksforGeeks
 * Difficulty: Medium
 *
 * Time Complexity: O(N^2) where N is array length
 * Space Complexity: O(N) for dp and count arrays
 *
 * Approach:
 *   - While standard LIS tracks the length of the longest increasing subsequence,
 *     here we must also count HOW MANY distinct subsequences achieve that maximum length.
 *   - Maintain two arrays:
 *     1. dp[i]: Length of LIS ending at index i.
 *     2. count[i]: Number of LIS of length dp[i] ending at index i.
 *   - Transitions for each prev < i:
 *     If nums[prev] < nums[i]:
 *       - If 1 + dp[prev] > dp[i]:
 *           Found a strictly longer subsequence!
 *           dp[i] = 1 + dp[prev]
 *           count[i] = count[prev]  (Inherit count from prev)
 *       - Else if 1 + dp[prev] == dp[i]:
 *           Found another subsequence of the same maximum length!
 *           count[i] += count[prev] (Accumulate ways)
 *   - Sum count[i] for all indices where dp[i] == maxLen.
 */

import java.util.Arrays;

class NumberOfLongestIncreasingSubsequences {

    public static int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        if (n <= 1) return n;

        int[] dp = new int[n];
        int[] count = new int[n];
        Arrays.fill(dp, 1);
        Arrays.fill(count, 1);

        int maxLen = 1;

        for (int i = 0; i < n; i++) {
            for (int prev = 0; prev < i; prev++) {
                if (nums[prev] < nums[i]) {
                    if (1 + dp[prev] > dp[i]) {
                        dp[i] = 1 + dp[prev];
                        count[i] = count[prev];
                    } else if (1 + dp[prev] == dp[i]) {
                        count[i] += count[prev];
                    }
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }

        int totalCount = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] == maxLen) {
                totalCount += count[i];
            }
        }

        return totalCount;
    }

    public static void main(String[] args) {
        // Test Case 1: nums = [1,3,5,4,7] -> Expected: 2 ([1, 3, 4, 7] and [1, 3, 5, 7])
        int[] nums1 = {1, 3, 5, 4, 7};
        System.out.println("Test 1: " + findNumberOfLIS(nums1)); // 2

        // Test Case 2: nums = [2,2,2,2,2] -> Expected: 5 (Each single element has len 1)
        int[] nums2 = {2, 2, 2, 2, 2};
        System.out.println("Test 2: " + findNumberOfLIS(nums2)); // 5
    }
}
