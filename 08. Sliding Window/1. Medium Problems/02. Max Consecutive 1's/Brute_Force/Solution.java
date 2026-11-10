/*
QUESTION:
Given a binary array `nums` and an integer `k`, return the maximum number of consecutive 1's in the array if you can flip at most `k` 0's.

APPROACH:
- Inspect all subarrays `nums[i...j]`.
- Count the number of zeros in the subarray.
- If zeros <= k, the subarray can be flipped to all 1's. Update `maxLen = Math.max(maxLen, j - i + 1)`.

TIME COMPLEXITY: O(N^2) inspecting all subarrays.
SPACE COMPLEXITY: O(1) constant auxiliary space.
*/

class Solution {
    public static int longestOnes(int[] nums, int k) {
        int n = nums.length;
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            int zeros = 0;
            for (int j = i; j < n; j++) {
                if (nums[j] == 0) zeros++;
                if (zeros <= k) {
                    maxLen = Math.max(maxLen, j - i + 1);
                } else {
                    break;
                }
            }
        }
        return maxLen;
    }
}
