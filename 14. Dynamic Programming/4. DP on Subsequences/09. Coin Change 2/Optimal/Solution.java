/*
QUESTION:
Count number of combinations that make up amount using coins.

APPROACH:
- Space-Optimized 1D Tabulation:
- `dp[a]` stores number of ways to make amount `a`.
- For each coin: `dp[a] += dp[a - coin]`.

TIME COMPLEXITY: O(N * Amount).
SPACE COMPLEXITY: O(Amount) 1D array.
*/

class Solution {
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for (int coin : coins) {
            for (int a = coin; a <= amount; a++) {
                dp[a] += dp[a - coin];
            }
        }

        return dp[amount];
    }
}
