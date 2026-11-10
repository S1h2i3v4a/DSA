/*
QUESTION:
Determine if you can reach the last index in O(N) using Greedy approach.

APPROACH:
- Maintain `maxReach`: the furthest index that can be reached so far.
- Iterate through array with index `i`:
  - If `i > maxReach`: current index is unreachable, return false.
  - Update `maxReach = Math.max(maxReach, i + nums[i])`.
  - If `maxReach >= n - 1`, return true.

TIME COMPLEXITY: O(N) single pass.
SPACE COMPLEXITY: O(1) constant auxiliary space.
*/

class Solution {
    public boolean canJump(int[] nums) {
        int maxReach = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            if (i > maxReach) return false;
            maxReach = Math.max(maxReach, i + nums[i]);
            if (maxReach >= n - 1) return true;
        }
        return true;
    }
}
