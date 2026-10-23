/**
 * Problem Name: Largest Divisible Subset
 * Platform: LeetCode (LC 368) / GeeksforGeeks
 * Difficulty: Medium
 *
 * Time Complexity: O(N^2) where N is the length of nums
 * Space Complexity: O(N) for dp and parent arrays
 *
 * Approach:
 *   - A subset is divisible if for every pair (a, b), either a % b == 0 or b % a == 0.
 *   - Key Mathematical Insight:
 *     If we sort the array ascendingly:
 *       If nums[i] % nums[prev] == 0, then by transitivity nums[i] is also divisible by
 *       all elements that divide nums[prev]!
 *   - This reduces the problem directly to the Longest Increasing Subsequence (LIS) pattern:
 *     1. Sort `nums` in ascending order.
 *     2. dp[i] = length of largest divisible subset ending at index i.
 *     3. For each i, for each prev < i:
 *          if nums[i] % nums[prev] == 0 and 1 + dp[prev] > dp[i]:
 *            dp[i] = 1 + dp[prev]
 *            parent[i] = prev
 *     4. Backtrack using parent pointers from the index with maximum dp[i].
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
