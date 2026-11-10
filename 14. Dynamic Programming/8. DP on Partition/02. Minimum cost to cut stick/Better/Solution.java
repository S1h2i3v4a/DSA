/*
QUESTION:
Minimum Cost to Cut a Stick.

APPROACH:
- Memoization: 2D table `dp[c+2][c+2]`.

TIME COMPLEXITY: O(C^3).
SPACE COMPLEXITY: O(C^2) table + O(C) stack.
*/

import java.util.Arrays;

class Solution {
    public int minCost(int n, int[] cuts) {
        int c = cuts.length;
        int[] sortedCuts = new int[c + 2];
        System.arraycopy(cuts, 0, sortedCuts, 1, c);
        sortedCuts[0] = 0;
        sortedCuts[c + 1] = n;
        Arrays.sort(sortedCuts);

        int[][] dp = new int[c + 2][c + 2];
        for (int[] row : dp) Arrays.fill(row, -1);
        return solve(1, c, sortedCuts, dp);
    }

    private int solve(int i, int j, int[] cuts, int[][] dp) {
        if (i > j) return 0;
        if (dp[i][j] != -1) return dp[i][j];

        int mini = Integer.MAX_VALUE;
        for (int k = i; k <= j; k++) {
            int cost = (cuts[j + 1] - cuts[i - 1]) + solve(i, k - 1, cuts, dp) + solve(k + 1, j, cuts, dp);
            mini = Math.min(mini, cost);
        }

        return dp[i][j] = mini;
    }
}
