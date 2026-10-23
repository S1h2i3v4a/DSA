/*
QUESTION:
Given an integer array arr, partition the array into (contiguous) subarrays of length at most k.
After partitioning, each subarray has their values changed to become the maximum value of that subarray.
Return the largest sum of the given array after partitioning. Test cases are generated so that the answer fits in a 32-bit integer.

Example:
Input: arr = [1, 15, 7, 9, 2, 5, 10], k = 3
Output: 84
Explanation: arr becomes [15, 15, 15, 9, 10, 10, 10]

APPROACH:
- This problem can be solved using dynamic programming.
- Define a 1D DP array `dp` where dp[i] represents the largest sum of the array after partitioning from index i to the end.
- Iterate through each index i of the array in reverse order.
- For each index i, iterate through all possible partition lengths from 1 to k.
- Calculate the maximum sum for the current partition, which is the maximum element within the partition multiplied by the length of the partition.
- Update dp[i] with the maximum value among all possible partition scenarios.
- Finally, return dp[0], which represents the largest sum of the array after partitioning.

COMPLEXITY ANALYSIS:
- The time complexity of this approach is O(n * k), where n is the length of the array.
- The space complexity is O(n) due to the dp array.
- Overall, the algorithm runs in O(n * k) time and O(n) space.

CODE:
*/

import java.io.*;
import java.util.*;

// CODE:-
class PartitionArrayForMaximumSum {

    public static int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;
        int[] dp = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            int maxVal = 0;
            int maxSum = 0;
            int limit = Math.min(n, i + k);

            for (int j = i; j < limit; j++) {
                maxVal = Math.max(maxVal, arr[j]);
                int len = j - i + 1;
                int sum = len * maxVal + dp[j + 1];
                maxSum = Math.max(maxSum, sum);
            }
            dp[i] = maxSum;
        }

        return dp[0];
    }

    public static void main(String[] args) {
        // Test Case 1: arr = [1,15,7,9,2,5,10], k = 3 -> Expected: 84 ([15,15,15, 9, 10,10,10] = 45 + 9 + 30 = 84)
        int[] arr1 = {1, 15, 7, 9, 2, 5, 10};
        System.out.println("Test 1: " + maxSumAfterPartitioning(arr1, 3)); // 84

        // Test Case 2: arr = [1,4,1,5,7,3,6,1,9,9,3], k = 4 -> Expected: 83
        int[] arr2 = {1, 4, 1, 5, 7, 3, 6, 1, 9, 9, 3};
        System.out.println("Test 2: " + maxSumAfterPartitioning(arr2, 4)); // 83
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
