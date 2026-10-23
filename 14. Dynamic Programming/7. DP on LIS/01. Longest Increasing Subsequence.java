/*
QUESTION:
Given an integer array nums, return the length of the longest strictly increasing subsequence.

APPROACH:
- This problem can be solved using dynamic programming.
- Let dp[i] represent the length of the longest increasing subsequence ending at index i.
- For each element at index i, we iterate through all the previous indices (prev) from 0 to i-1.
- If nums[prev] is smaller than nums[i], it means we can extend the increasing subsequence ending at prev by including nums[i].
- We update dp[i] as max(dp[prev] + 1, dp[i]).
- Initialize a variable ans to keep track of the maximum value in dp.
- Return ans as the length of the longest increasing subsequence.

COMPLEXITY ANALYSIS:
- The time complexity of this approach is O(n^2), where n is the length of the input array nums.
- The space complexity is O(n) due to the dp array.
- Overall, the algorithm runs in O(n^2) time and O(n) space.

CODE:
*/

import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// CODE:-
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
