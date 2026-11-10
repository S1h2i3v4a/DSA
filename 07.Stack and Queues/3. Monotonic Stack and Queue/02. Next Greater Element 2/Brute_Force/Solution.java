/*
QUESTION:
Given a circular integer array `nums`, return the next greater number for every element in `nums`.
The next greater number of a number x is the first greater number to its traversing-order next in the array,
which means you could search circularly to find its next greater number. If it doesn't exist, return -1.

APPROACH:
- For each element `nums[i]`, inspect the next `n - 1` elements circularly using index `(i + j) % n`.
- Return the first element strictly greater than `nums[i]`. If none found, assign -1.

TIME COMPLEXITY: O(N^2) nested loops for circular traversal.
SPACE COMPLEXITY: O(1) auxiliary space.
*/

class Solution {
    public static int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            result[i] = -1;
            for (int j = 1; j < n; j++) {
                int nextIdx = (i + j) % n;
                if (nums[nextIdx] > nums[i]) {
                    result[i] = nums[nextIdx];
                    break;
                }
            }
        }
        return result;
    }
}
