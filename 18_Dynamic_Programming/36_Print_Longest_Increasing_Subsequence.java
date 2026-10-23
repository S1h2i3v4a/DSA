/**
 * Problem Name: Print Longest Increasing Subsequence
 * Platform: GeeksforGeeks / Standard Data Structures & Algorithms
 * Difficulty: Medium
 *
 * Time Complexity: O(N^2) where N is array length
 * Space Complexity: O(N) for dp and parent tracking arrays
 *
 * Approach:
 *   - While finding the LIS length can be done in O(N log N), reconstructing the actual
 *     subsequence elements is cleanly achieved using an O(N^2) DP approach with parent pointers:
 *     1. dp[i] stores the length of the LIS ending at index i.
 *     2. parent[i] stores the index of the predecessor of element nums[i] in that LIS.
 *     3. Keep track of the index `lastIndex` that yields the maximum value in dp[].
 *     4. Backtrack from `lastIndex` using the parent pointers:
 *        lastIndex = parent[lastIndex], collecting elements.
 *     5. Reverse the collected elements to produce the LIS in original order.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
