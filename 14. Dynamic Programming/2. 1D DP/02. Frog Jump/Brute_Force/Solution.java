/*
QUESTION:
Geek wants to climb from the 0th stair to the (n-1)th stair. At a time, the Geek can climb either 1 or 2 steps.
Return the minimum energy that can be used by the Geek to jump from stair 0 to stair N-1.

APPROACH:
- Pure recursion from step i:
- Can jump to i+1 with cost abs(height[i+1] - height[i]).
- Can jump to i+2 with cost abs(height[i+2] - height[i]).
- Take minimum of both branches.

TIME COMPLEXITY: O(2^N) exponential recursion tree.
SPACE COMPLEXITY: O(N) recursion stack.
*/

class Solution {
    public int minimumEnergy(int[] height, int n) {
        return solve(n - 1, height);
    }

    private int solve(int i, int[] height) {
        if (i == 0) return 0;
        int jumpOne = solve(i - 1, height) + Math.abs(height[i] - height[i - 1]);
        int jumpTwo = Integer.MAX_VALUE;
        if (i > 1) {
            jumpTwo = solve(i - 2, height) + Math.abs(height[i] - height[i - 2]);
        }
        return Math.min(jumpOne, jumpTwo);
    }
}
