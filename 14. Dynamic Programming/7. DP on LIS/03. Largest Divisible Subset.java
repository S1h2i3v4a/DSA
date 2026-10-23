/*
QUESTION:
Given a set of distinct positive integers nums, return the largest subset answer such that every pair (answer[i], answer[j]) of elements in this subset satisfies:
- answer[i] % answer[j] == 0, or
- answer[j] % answer[i] == 0
If there are multiple solutions, return any of them.

APPROACH:
- This problem can be solved using dynamic programming.
- Sort the input array nums in ascending order.
- Let dp[i] represent the size of the largest divisible subset ending at index i.
- Additionally, maintain a prevmap to track the previous index in the subset.
- Iterate through the sorted array and for each index i, iterate through all previous indices (prev) from 0 to i-1.
- If nums[i] is divisible by nums[prev] and dp[i] < dp[prev] + 1, update dp[i] and prevmap[i].
- Find the index i where dp has the maximum value.
- Construct the largest divisible subset using the prevmap and the found index i.
- Return the subset.

COMPLEXITY ANALYSIS:
- The time complexity of this approach is O(n^2), where n is the length of the input array nums.
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
class LargestDivisibleSubset {

    public static List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
        if (n == 0) return new ArrayList<>();

        Arrays.sort(nums); // Mandatory sorting

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
                if (nums[i] % nums[prev] == 0 && 1 + dp[prev] > dp[i]) {
                    dp[i] = 1 + dp[prev];
                    parent[i] = prev;
                }
            }
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                lastIndex = i;
            }
        }

        List<Integer> result = new ArrayList<>();
        result.add(nums[lastIndex]);

        while (parent[lastIndex] != lastIndex) {
            lastIndex = parent[lastIndex];
            result.add(nums[lastIndex]);
        }

        Collections.reverse(result);
        return result;
    }

    public static void main(String[] args) {
        // Test Case 1: nums = [1,2,3] -> Expected: [1,2] or [1,3]
        int[] nums1 = {1, 2, 3};
        System.out.println("Test 1: " + largestDivisibleSubset(nums1));

        // Test Case 2: nums = [1,2,4,8] -> Expected: [1,2,4,8]
        int[] nums2 = {1, 2, 4, 8};
        System.out.println("Test 2: " + largestDivisibleSubset(nums2));
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
