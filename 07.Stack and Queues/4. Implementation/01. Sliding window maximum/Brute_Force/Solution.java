/*
QUESTION:
You are given an array of integers `nums`, there is a sliding window of size `k` which is moving from the very left of the array to the very right.
Return the max sliding window.

APPROACH:
- For each starting position `i` from 0 to `n - k`, find maximum in range `nums[i ... i + k - 1]`.
- Store the maximum in result array.

TIME COMPLEXITY: O(N * K) where N is array length and K is window size.
SPACE COMPLEXITY: O(1) auxiliary space (excluding result).
*/

class Solution {
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (n == 0 || k == 0) return new int[0];
        int[] result = new int[n - k + 1];

        for (int i = 0; i <= n - k; i++) {
            int maxVal = nums[i];
            for (int j = 1; j < k; j++) {
                maxVal = Math.max(maxVal, nums[i + j]);
            }
            result[i] = maxVal;
        }
        return result;
    }
}
