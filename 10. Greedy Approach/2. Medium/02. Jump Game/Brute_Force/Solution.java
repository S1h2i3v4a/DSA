/*
QUESTION:
You are given an integer array `nums`. You are initially positioned at the array's first index,
and each element in the array represents your maximum jump length at that position.
Return true if you can reach the last index, or false otherwise.

APPROACH:
- Recursive backtracking: from current index `curr`, try jumping `1, 2, ..., nums[curr]` steps.
- If any jump path reaches `n - 1`, return true.

TIME COMPLEXITY: O(2^N) exponential branching.
SPACE COMPLEXITY: O(N) recursion stack.
*/

class Solution {
    private boolean canJumpFrom(int pos, int[] nums) {
        if (pos >= nums.length - 1) return true;
        int furthest = Math.min(pos + nums[pos], nums.length - 1);
        for (int next = furthest; next > pos; next--) {
            if (canJumpFrom(next, nums)) return true;
        }
        return false;
    }

    public boolean canJump(int[] nums) {
        return canJumpFrom(0, nums);
    }
}
