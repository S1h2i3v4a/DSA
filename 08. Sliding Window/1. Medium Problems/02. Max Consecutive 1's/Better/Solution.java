/*
QUESTION:
Given a binary array `nums` and an integer `k`, return the maximum number of consecutive 1's if you can flip at most `k` 0's.

APPROACH:
- Two-pointer sliding window `[left, right]`.
- Expand `right`: if `nums[right] == 0`, increment `zeros`.
- While `zeros > k`, if `nums[left] == 0` decrement `zeros`, and increment `left`.
- Update `maxLen = Math.max(maxLen, right - left + 1)`.

TIME COMPLEXITY: O(2 * N) = O(N) since left and right each move at most N steps.
SPACE COMPLEXITY: O(1) constant auxiliary space.
*/

class Solution {
    public static int longestOnes(int[] nums, int k) {
        int n = nums.length;
        int left = 0, zeros = 0, maxLen = 0;

        for (int right = 0; right < n; right++) {
            if (nums[right] == 0) zeros++;
            while (zeros > k) {
                if (nums[left] == 0) zeros--;
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}
