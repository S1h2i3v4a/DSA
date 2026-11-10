/*
QUESTION:
Find maximum profit with unlimited transactions, but after selling you cannot buy on next day (cooldown 1 day).

APPROACH:
- Pure recursion: selling moves state to i + 2 instead of i + 1.

TIME COMPLEXITY: O(2^N).
SPACE COMPLEXITY: O(N) recursion stack.
*/

class Solution {
    public int maxProfit(int[] prices) {
        return solve(0, 1, prices);
    }

    private int solve(int i, int canBuy, int[] prices) {
        if (i >= prices.length) return 0;

        if (canBuy == 1) {
            int buy = -prices[i] + solve(i + 1, 0, prices);
            int skip = solve(i + 1, 1, prices);
            return Math.max(buy, skip);
        } else {
            int sell = prices[i] + solve(i + 2, 1, prices);
            int skip = solve(i + 1, 0, prices);
            return Math.max(sell, skip);
        }
    }
}
