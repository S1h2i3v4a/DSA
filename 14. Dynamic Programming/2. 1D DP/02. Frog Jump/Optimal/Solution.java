/*
QUESTION:
Geek wants to climb from the 0th stair to the (n-1)th stair. At a time, the Geek can climb either 1 or 2 steps.
Return the minimum energy that can be used by the Geek to jump from stair 0 to stair N-1.

APPROACH:
- Space-Optimized Bottom-Up Tabulation:
- Keep track of `prev` (cost to reach i-1) and `prev2` (cost to reach i-2).
- Update them iteratively in a single loop.

TIME COMPLEXITY: O(N) linear time.
SPACE COMPLEXITY: O(1) constant auxiliary space.
*/

class Solution {
    public int minimumEnergy(int[] height, int n) {
        if (n <= 1) return 0;
        int prev2 = 0;
        int prev = Math.abs(height[1] - height[0]);

        for (int i = 2; i < n; i++) {
            int jumpOne = prev + Math.abs(height[i] - height[i - 1]);
            int jumpTwo = prev2 + Math.abs(height[i] - height[i - 2]);
            int cur = Math.min(jumpOne, jumpTwo);
            prev2 = prev;
            prev = cur;
        }

        return prev;
    }
}
