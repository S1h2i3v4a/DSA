/*
QUESTION:
Geek wants to climb from the 0th stair to the (n-1)th stair. At a time, the Geek can climb either 1 or 2 steps.
Return the minimum energy that can be used by the Geek to jump from stair 0 to stair N-1.

APPROACH:
- Memoization (Top-down DP):
- Store computed minimum energy for each stair in `dp` array of size n.

TIME COMPLEXITY: O(N) each stair evaluated once.
SPACE COMPLEXITY: O(N) recursion stack + dp array.
*/

import java.util.Arrays;

class Solution {
    public int minimumEnergy(int[] height, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return solve(n - 1, height, dp);
    }

    private int solve(int i, int[] height, int[] dp) {
        if (i == 0) return 0;
        if (dp[i] != -1) return dp[i];
        int jumpOne = solve(i - 1, height, dp) + Math.abs(height[i] - height[i - 1]);
        int jumpTwo = Integer.MAX_VALUE;
        if (i > 1) {
            jumpTwo = solve(i - 2, height, dp) + Math.abs(height[i] - height[i - 2]);
        }
        return dp[i] = Math.min(jumpOne, jumpTwo);
    }
}
