/*
QUESTION:
Minimum Cost to Cut a Stick.

APPROACH:
- Bottom-Up Tabulation:
- `dp[i][j] = min_{i <= k <= j}((cuts[j+1] - cuts[i-1]) + dp[i][k-1] + dp[k+1][j])`.

TIME COMPLEXITY: O(C^3).
SPACE COMPLEXITY: O(C^2) dp table.
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

        for (int i = c; i >= 1; i--) {
            for (int j = i; j <= c; j++) {
                int mini = Integer.MAX_VALUE;
                for (int k = i; k <= j; k++) {
                    int cost = (sortedCuts[j + 1] - sortedCuts[i - 1]) + dp[i][k - 1] + dp[k + 1][j];
                    mini = Math.min(mini, cost);
                }
                dp[i][j] = mini;
            }
        }

        return dp[1][c];
    }
}
