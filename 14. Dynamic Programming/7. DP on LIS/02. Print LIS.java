/*
QUESTION:
Given an integer n and an array of integers, return the Longest Increasing Subsequence which is lexicographically smallest corresponding to the indices of the elements.

APPROACH:
- This problem can be solved using dynamic programming.
- Let dp[i] represent the length of the longest increasing subsequence ending at index i.
- Additionally, maintain a prevmap to track the previous index in the increasing subsequence.
- Iterate through the array and for each index i, iterate through all previous indices (prev) from 0 to i-1.
- If arr[prev] < arr[i] and dp[i] < dp[prev] + 1, update dp[i] and prevmap[i].
- Find the index i where dp has the maximum value.
- Construct the Longest Increasing Subsequence using the prevmap and the found index i.
- Return the LIS in reverse order to get the lexicographically smallest sequence.

COMPLEXITY ANALYSIS:
- The time complexity of this approach is O(n^2), where n is the length of the input array arr.
- The space complexity is O(n) due to the dp and prevmap arrays, as well as the lis vector.
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
class PrintLongestIncreasingSubsequence {

    public static List<Integer> printLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int[] parent = new int[n];
        Arrays.fill(dp, 1);

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int maxLen = 1;
        int lastIndex = 0;

        for (int i = 0; i < n; i++) {
            for (int prev = 0; prev < i; prev++) {
                if (nums[prev] < nums[i] && 1 + dp[prev] > dp[i]) {
                    dp[i] = 1 + dp[prev];
                    parent[i] = prev;
                }
            }
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                lastIndex = i;
            }
        }

        // Backtrack to collect elements
        List<Integer> lis = new ArrayList<>();
        lis.add(nums[lastIndex]);

        while (parent[lastIndex] != lastIndex) {
            lastIndex = parent[lastIndex];
            lis.add(nums[lastIndex]);
        }

        Collections.reverse(lis);
        return lis;
    }

    public static void main(String[] args) {
        // Test Case 1: nums = [10, 9, 2, 5, 3, 7, 101, 18] -> Expected: [2, 3, 7, 101] or [2, 3, 7, 18]
        int[] nums1 = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("Test 1: " + printLIS(nums1));

        // Test Case 2: nums = [0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15]
        int[] nums2 = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
        System.out.println("Test 2: " + printLIS(nums2));
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
