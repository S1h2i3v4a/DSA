/*
QUESTION:
You can buy and sell stock multiple times. Return maximum profit achievable.

APPROACH:
- Pure recursion: at day i with state `canBuy`:
  - If canBuy: choose to buy (-prices[i] + solve(i + 1, false)) or skip (solve(i + 1, true)).
  - Else: choose to sell (+prices[i] + solve(i + 1, true)) or skip (solve(i + 1, false)).

TIME COMPLEXITY: O(2^N).
SPACE COMPLEXITY: O(N) recursion stack.
*/

class Solution {
    public int maxProfit(int[] prices) {
        return solve(0, 1, prices);
    }

    private int solve(int i, int canBuy, int[] prices) {
        if (i == prices.length) return 0;
        if (canBuy == 1) {
            int buy = -prices[i] + solve(i + 1, 0, prices);
            int skip = solve(i + 1, 1, prices);
            return Math.max(buy, skip);
        } else {
            int sell = prices[i] + solve(i + 1, 1, prices);
            int skip = solve(i + 1, 0, prices);
            return Math.max(sell, skip);
        }
    }
}
