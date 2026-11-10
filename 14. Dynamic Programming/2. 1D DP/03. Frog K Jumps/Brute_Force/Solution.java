/*
QUESTION:
Geek can jump anywhere from 1 to k steps. Find the minimum energy to reach stair n-1.

APPROACH:
- Recursion: at index i, loop through step j from 1 to k, jumping from i - j.
- Min energy = min_{1 <= j <= k}(solve(i - j) + abs(height[i] - height[i - j])).

TIME COMPLEXITY: O(K^N) exponential branching of factor K.
SPACE COMPLEXITY: O(N) recursion stack.
*/

class Solution {
    public int minimizeCost(int[] height, int n, int k) {
        return solve(n - 1, height, k);
    }

    private int solve(int i, int[] height, int k) {
        if (i == 0) return 0;
        int minSteps = Integer.MAX_VALUE;
        for (int j = 1; j <= k; j++) {
            if (i - j >= 0) {
                int jump = solve(i - j, height, k) + Math.abs(height[i] - height[i - j]);
                minSteps = Math.min(minSteps, jump);
            }
        }
        return minSteps;
    }
}
