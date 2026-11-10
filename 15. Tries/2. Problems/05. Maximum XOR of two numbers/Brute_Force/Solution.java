/*
QUESTION:
Given integer array nums, return maximum result of nums[i] XOR nums[j].

APPROACH:
- Iterate through all pairs (i, j) with nested loops and compute XOR.
- Track maximum value.

TIME COMPLEXITY: O(N^2).
SPACE COMPLEXITY: O(1).
*/

class Solution {
    public static int findMaximumXOR(int[] nums) {
        int maxResult = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                maxResult = Math.max(maxResult, nums[i] ^ nums[j]);
            }
        }
        return maxResult;
    }
}
