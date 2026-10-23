/**
 * Problem Name: Longest Increasing Subsequence (LIS)
 * Platform: LeetCode (LC 300) / GeeksforGeeks
 * Difficulty: Medium
 *
 * Time Complexity:
 *   - Approach 1 (DP Tabulation): O(N^2)
 *   - Approach 2 (Binary Search / Patience Sorting): O(N log N)
 * Space Complexity: O(N) for DP / tails array
 *
 * Approach:
 *   - Find the length of the longest strictly increasing subsequence.
 *   - Approach 1 (O(N^2) DP):
 *     dp[i] = length of LIS ending at index i.
 *     dp[i] = 1 + max_{0 <= j < i, nums[j] < nums[i]} dp[j].
 *   - Approach 2 (O(N log N) Binary Search):
 *     Maintain an array `tails` where tails[len] is the smallest tail of all increasing
 *     subsequences of length len + 1.
 *     For each number x in nums:
 *       - Binary search for the first element in tails >= x.
 *       - If found, replace it with x (makes potential future extensions easier).
 *       - If x is larger than all elements, append x to tails.
 *     The length of `tails` at the end is the length of the LIS!
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class LongestIncreasingSubsequence {

    // ==========================================
    // Approach 1: O(N^2) DP Tabulation
    // ==========================================
    public static int lengthOfLIS_DP(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int maxLen = 1;

        for (int i = 0; i < n; i++) {
            for (int prev = 0; prev < i; prev++) {
                if (nums[prev] < nums[i]) {
                    dp[i] = Math.max(dp[i], 1 + dp[prev]);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }

    // ==========================================
    // Approach 2: O(N log N) Binary Search (Patience Sorting)
    // ==========================================
    public static int lengthOfLIS_BinarySearch(int[] nums) {
        List<Integer> tails = new ArrayList<>();

        for (int x : nums) {
            int idx = Collections.binarySearch(tails, x);
            if (idx < 0) {
                idx = -idx - 1; // Insertion point
            }
            if (idx == tails.size()) {
                tails.add(x);
            } else {
                tails.set(idx, x);
            }
        }

        return tails.size();
    }

    public static void main(String[] args) {
        // Test Case 1: nums = [10,9,2,5,3,7,101,18] -> Expected: 4 ([2,3,7,101] or [2,3,7,18])
        int[] nums1 = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("Test 1 (DP): " + lengthOfLIS_DP(nums1)); // 4
        System.out.println("Test 1 (BS): " + lengthOfLIS_BinarySearch(nums1)); // 4

        // Test Case 2: nums = [0,1,0,3,2,3] -> Expected: 4 ([0,1,2,3])
        int[] nums2 = {0, 1, 0, 3, 2, 3};
        System.out.println("Test 2 (BS): " + lengthOfLIS_BinarySearch(nums2)); // 4

        // Test Case 3: nums = [7,7,7,7,7,7,7] -> Expected: 1
        int[] nums3 = {7, 7, 7, 7, 7, 7, 7};
        System.out.println("Test 3 (BS): " + lengthOfLIS_BinarySearch(nums3)); // 1
    }
}
