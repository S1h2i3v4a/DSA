/**
 * Problem Name: Longest Bitonic Subsequence
 * Platform: GeeksforGeeks / Coding Ninjas
 * Difficulty: Medium
 *
 * Time Complexity: O(N^2) where N is array length
 * Space Complexity: O(N) for two DP arrays
 *
 * Approach:
 *   - A bitonic sequence is a sequence of numbers that first strictly increases and then
 *     strictly decreases.
 *   - Algorithm:
 *     1. Compute `dp1[i]`: Length of Longest Increasing Subsequence (LIS) ending at index i
 *        (traversed left to right).
 *     2. Compute `dp2[i]`: Length of Longest Decreasing Subsequence (LDS) starting at index i
 *        (equivalent to LIS ending at i traversed right to left).
 *     3. At any peak element i, the length of the bitonic subsequence is:
 *        bitonicLen = dp1[i] + dp2[i] - 1 (subtract 1 since peak i is counted in both).
 *     4. The maximum bitonicLen over all 0 <= i < n is the answer.
 */

import java.util.Arrays;

class LongestBitonicSubsequence {

    public static int longestBitonicSequence(int n, int[] nums) {
        int[] dp1 = new int[n]; // LIS left-to-right
        int[] dp2 = new int[n]; // LIS right-to-left (LDS)

        Arrays.fill(dp1, 1);
        Arrays.fill(dp2, 1);

        // Step 1: LIS left to right
        for (int i = 0; i < n; i++) {
            for (int prev = 0; prev < i; prev++) {
                if (nums[prev] < nums[i]) {
                    dp1[i] = Math.max(dp1[i], 1 + dp1[prev]);
                }
            }
        }

        // Step 2: LIS right to left
        for (int i = n - 1; i >= 0; i--) {
            for (int prev = n - 1; prev > i; prev--) {
                if (nums[prev] < nums[i]) {
                    dp2[i] = Math.max(dp2[i], 1 + dp2[prev]);
                }
            }
        }

        // Step 3: Find maximum bitonic sequence
        int maxBitonic = 0;
        for (int i = 0; i < n; i++) {
            maxBitonic = Math.max(maxBitonic, dp1[i] + dp2[i] - 1);
        }

        return maxBitonic;
    }

    public static void main(String[] args) {
        // Test Case 1: nums = [1, 2, 5, 3, 2] -> Expected: 5 ([1, 2, 5, 3, 2])
        int[] nums1 = {1, 2, 5, 3, 2};
        System.out.println("Test 1: " + longestBitonicSequence(nums1.length, nums1)); // 5

        // Test Case 2: nums = [1, 11, 2, 10, 4, 5, 2, 1] -> Expected: 6 ([1, 2, 10, 4, 2, 1] or [1, 2, 4, 5, 2, 1])
        int[] nums2 = {1, 11, 2, 10, 4, 5, 2, 1};
        System.out.println("Test 2: " + longestBitonicSequence(nums2.length, nums2)); // 6
    }
}
