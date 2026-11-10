/*
QUESTION:
Given coins array and amount, find fewest number of coins needed.

APPROACH:
- Space-Optimized 1D Tabulation:
- `dp[a]` stores minimum coins to make amount `a`.
- `dp[a] = min(dp[a], 1 + dp[a - coin])`.

TIME COMPLEXITY: O(N * Amount).
SPACE COMPLEXITY: O(Amount) 1D array.
*/

import java.util.Arrays;

class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int coin : coins) {
            for (int a = coin; a <= amount; a++) {
                dp[a] = Math.min(dp[a], 1 + dp[a - coin]);
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }
}
