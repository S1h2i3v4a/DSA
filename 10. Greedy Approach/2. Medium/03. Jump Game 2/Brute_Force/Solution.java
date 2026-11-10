/*
QUESTION:
Given a 0-indexed array of integers `nums` of length `n`. Return the minimum number of jumps to reach `nums[n - 1]`.

APPROACH:
- Recursive backtracking: explore all jumps from index `i` to `i + nums[i]`.
- Return minimum jumps required to reach last index.

TIME COMPLEXITY: O(N!) exponential in worst case.
SPACE COMPLEXITY: O(N) recursion stack.
*/

class Solution {
    private int minJumps(int idx, int[] nums) {
        if (idx >= nums.length - 1) return 0;
        int min = 100000;
        for (int j = 1; j <= nums[idx]; j++) {
            min = Math.min(min, 1 + minJumps(idx + j, nums));
        }
        return min;
    }

    public int jump(int[] nums) {
        return minJumps(0, nums);
    }
}
