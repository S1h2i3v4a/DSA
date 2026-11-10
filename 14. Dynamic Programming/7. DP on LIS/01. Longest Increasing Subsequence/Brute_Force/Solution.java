/*
QUESTION:
Given an integer array nums, return the length of the longest strictly increasing subsequence.

APPROACH:
- Pure recursion: at index i with previous index prev_idx:
  - Can include nums[i] if prev_idx == -1 or nums[i] > nums[prev_idx].
  - Can always exclude nums[i].

TIME COMPLEXITY: O(2^N).
SPACE COMPLEXITY: O(N) recursion stack.
*/

class Solution {
    public int lengthOfLIS(int[] nums) {
        return solve(0, -1, nums);
    }

    private int solve(int i, int prevIdx, int[] nums) {
        if (i == nums.length) return 0;
        int notTake = solve(i + 1, prevIdx, nums);
        int take = 0;
        if (prevIdx == -1 || nums[i] > nums[prevIdx]) {
            take = 1 + solve(i + 1, i, nums);
        }
        return Math.max(notTake, take);
    }
}
