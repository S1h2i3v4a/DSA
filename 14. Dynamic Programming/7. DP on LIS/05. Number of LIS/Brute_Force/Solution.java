/*
QUESTION:
Given an integer array nums, return the number of longest increasing subsequences.

APPROACH:
- Pure recursion generating all increasing subsequences, counting occurrences of max length.

TIME COMPLEXITY: O(2^N).
SPACE COMPLEXITY: O(N) recursion stack.
*/

class Solution {
    private int maxLen = 0;
    private int count = 0;

    public int findNumberOfLIS(int[] nums) {
        maxLen = 0;
        count = 0;
        solve(0, -1, 0, nums);
        return count;
    }

    private void solve(int i, int prevIdx, int curLen, int[] nums) {
        if (i == nums.length) {
            if (curLen > maxLen) {
                maxLen = curLen;
                count = 1;
            } else if (curLen == maxLen && curLen > 0) {
                count++;
            }
            return;
        }

        if (prevIdx == -1 || nums[i] > nums[prevIdx]) {
            solve(i + 1, i, curLen + 1, nums);
        }
        solve(i + 1, prevIdx, curLen, nums);
    }
}
