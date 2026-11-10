/*
QUESTION:
Given integer array arr, partition array into contiguous subarrays of length at most k.
After partitioning, each subarray's values become the maximum value of that subarray. Maximize sum.

APPROACH:
- Pure recursion: try all subarray sizes from 1 to k starting at index i.

TIME COMPLEXITY: O(K^N).
SPACE COMPLEXITY: O(N) recursion stack.
*/

class Solution {
    public int maxSumAfterPartitioning(int[] arr, int k) {
        return solve(0, arr, k);
    }

    private int solve(int i, int[] arr, int k) {
        if (i == arr.length) return 0;
        int maxVal = 0;
        int maxSum = 0;
        int limit = Math.min(arr.length, i + k);

        for (int j = i; j < limit; j++) {
            maxVal = Math.max(maxVal, arr[j]);
            int sum = maxVal * (j - i + 1) + solve(j + 1, arr, k);
            maxSum = Math.max(maxSum, sum);
        }

        return maxSum;
    }
}
