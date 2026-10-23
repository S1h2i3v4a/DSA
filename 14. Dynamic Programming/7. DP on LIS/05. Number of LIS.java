/*
QUESTION:
Given an integer array nums, return the number of longest increasing subsequences.
Notice that the sequence has to be strictly increasing.

APPROACH:
- This problem can be solved using dynamic programming.
- Compute two DP arrays: one for the length of the longest increasing subsequence ending at index i (`dp`) and another for the count of such subsequences (`cnt`).
- Iterate through the array and for each index i, iterate through all previous indices (prev) from 0 to i-1.
- If nums[prev] < nums[i], update the `dp` and `cnt` arrays accordingly.
- Find the maximum value in the `dp` array to get the length of the longest increasing subsequence (`lis`).
- Iterate through the `dp` array again and count the number of subsequences that have the length equal to `lis`.
- Return the total count of such subsequences.

COMPLEXITY ANALYSIS:
- The time complexity of this approach is O(n^2), where n is the length of the input array nums.
- The space complexity is O(n) due to the dp and cnt arrays.
- Overall, the algorithm runs in O(n^2) time and O(n) space.

CODE:
*/

import java.io.*;
import java.util.*;
import java.util.Arrays;

// CODE:-
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
