/**
 * Problem Name: Partition Array for Maximum Sum
 * Platform: LeetCode (LC 1043) / GeeksforGeeks
 * Difficulty: Medium
 *
 * Time Complexity: O(N * K) where N is array length and K is max partition size
 * Space Complexity: O(N) for DP table
 *
 * Approach:
 *   - Partition array `arr` into contiguous subarrays of length at most `k`.
 *   - After partitioning, each element in a subarray is changed to the maximum value of that subarray.
 *   - Return the largest sum of the given array after partitioning.
 *   - Front-Partitioning Recurrence:
 *     For index i, consider all partition lengths len from 1 up to k:
 *     Maintain maxVal of current window arr[i..i+len-1].
 *     cost = len * maxVal + dp[i + len].
 *     dp[i] = max_{1 <= len <= k} cost.
 *     Base Case: dp[n] = 0.
 */

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
