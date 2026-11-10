/*
QUESTION:
Count number of combinations that make up amount using coins.

APPROACH:
- Memoization: 2D table `dp[n][amount + 1]`.

TIME COMPLEXITY: O(N * Amount).
SPACE COMPLEXITY: O(N * Amount) table + O(Amount) stack.
*/

import java.util.Arrays;

class Solution {
    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n][amount + 1];
        for (int[] row : dp) Arrays.fill(row, -1);
        return solve(n - 1, amount, coins, dp);
    }

    private int solve(int i, int amount, int[] coins, int[][] dp) {
        if (amount == 0) return 1;
        if (i < 0) return 0;
        if (dp[i][amount] != -1) return dp[i][amount];

        int notTake = solve(i - 1, amount, coins, dp);
        int take = 0;
        if (coins[i] <= amount) take = solve(i, amount - coins[i], coins, dp);

        return dp[i][amount] = notTake + take;
    }
}
