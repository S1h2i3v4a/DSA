/*
QUESTION:
Given a binary array `nums` and an integer `k`, return the maximum number of consecutive 1's if you can flip at most `k` 0's.

APPROACH:
- Non-shrinking sliding window.
- Expand `right`: if `nums[right] == 0`, increment `zeros`.
- If `zeros > k`, shift `left` forward by at most 1 step: if `nums[left] == 0`, decrement `zeros`, then `left++`.
- The window size never shrinks, maintaining the maximum length found so far.
- At the end, `maxLen = right - left`.

TIME COMPLEXITY: O(N) single pass.
SPACE COMPLEXITY: O(1) constant auxiliary space.
*/

class Solution {
    public static int longestOnes(int[] nums, int k) {
        int left = 0, zeros = 0, right = 0;

        for (right = 0; right < nums.length; right++) {
            if (nums[right] == 0) zeros++;
            if (zeros > k) {
                if (nums[left] == 0) zeros--;
                left++;
            }
        }
        return right - left;
    }
}
