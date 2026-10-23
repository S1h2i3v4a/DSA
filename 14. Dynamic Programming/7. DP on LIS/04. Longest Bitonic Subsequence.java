/*
QUESTION:
Given an array of positive integers, find the maximum length of a Bitonic subsequence.
A subsequence of an array is called Bitonic if it is first strictly increasing and then strictly decreasing.

APPROACH:
- This problem can be solved using dynamic programming.
- Compute two DP arrays: one from the left and another from the right.
- The left DP array, `left`, represents the length of the longest increasing subsequence ending at index i.
- The right DP array, `right`, represents the length of the longest decreasing subsequence starting at index i.
- Iterate through the array and compute the `left` DP array, considering elements from the left to the right.
- Iterate through the array again and compute the `right` DP array, considering elements from the right to the left.
- The maximum length of the Bitonic subsequence is the maximum sum of the corresponding elements from the `left` and `right` DP arrays minus 1 (to avoid double counting the element at the peak).
- Return the maximum length.

COMPLEXITY ANALYSIS:
- The time complexity of this approach is O(n^2), where n is the length of the input array nums.
- The space complexity is O(n) due to the left and right DP arrays.
- Overall, the algorithm runs in O(n^2) time and O(n) space.

CODE:
*/

import java.io.*;
import java.util.*;
import java.util.Arrays;

// CODE:-
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
