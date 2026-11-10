/*
QUESTION:
Given an array `nums` of house values, return the maximum amount of money you can rob without robbing two adjacent houses.

APPROACH:
- Space-Optimized Bottom-Up Tabulation:
- Only the previous two max amounts (`prev` and `prev2`) are needed.
- `curr = max(nums[i] + prev2, prev)`.

TIME COMPLEXITY: O(N) single pass.
SPACE COMPLEXITY: O(1) constant auxiliary space.
*/

class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];

        int prev2 = 0;
        int prev = nums[0];

        for (int i = 1; i < n; i++) {
            int pick = nums[i] + prev2;
            int notPick = prev;
            int cur = Math.max(pick, notPick);
            prev2 = prev;
            prev = cur;
        }

        return prev;
    }
}
