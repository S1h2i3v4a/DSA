/*
QUESTION:
Given an array of integers `arr`, find the sum of `min(b)`, where `b` ranges over every (contiguous) subarray of `arr`.
Since the answer may be large, return the answer modulo 10^9 + 7.

APPROACH:
- Iterate through all possible subarrays using two nested loops `i` and `j`.
- Track the minimum element for each subarray `arr[i...j]` and add to total.

TIME COMPLEXITY: O(N^2) inspecting all contiguous subarrays.
SPACE COMPLEXITY: O(1) constant space.
*/

class Solution {
    public static int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        long total = 0;
        long MOD = 1000000007L;

        for (int i = 0; i < n; i++) {
            int minVal = arr[i];
            for (int j = i; j < n; j++) {
                minVal = Math.min(minVal, arr[j]);
                total = (total + minVal) % MOD;
            }
        }
        return (int) total;
    }
}
