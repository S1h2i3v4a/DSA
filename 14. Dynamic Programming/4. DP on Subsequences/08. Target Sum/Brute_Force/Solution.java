/*
QUESTION:
Assign '+' and '-' to each integer in nums to make sum equal to target. Return total number of ways.

APPROACH:
- Pure recursion: at index i, add nums[i] or subtract nums[i].
- solve(i, currentSum) = solve(i + 1, currentSum + nums[i]) + solve(i + 1, currentSum - nums[i]).

TIME COMPLEXITY: O(2^N).
SPACE COMPLEXITY: O(N) recursion stack.
*/

class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        return solve(0, 0, nums, target);
    }

    private int solve(int i, int currentSum, int[] nums, int target) {
        if (i == nums.length) {
            return currentSum == target ? 1 : 0;
        }
        return solve(i + 1, currentSum + nums[i], nums, target)
             + solve(i + 1, currentSum - nums[i], nums, target);
    }
}
