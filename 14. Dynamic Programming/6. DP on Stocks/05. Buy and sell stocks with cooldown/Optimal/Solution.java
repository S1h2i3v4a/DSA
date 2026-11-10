/*
QUESTION:
Best Time to Buy and Sell Stock with Cooldown.

APPROACH:
- Space-Optimized State Machine DP:
- `buy[i] = max(buy[i-1], rest[i-1] - prices[i])`
- `sell[i] = buy[i-1] + prices[i]`
- `rest[i] = max(rest[i-1], sell[i-1])`.

TIME COMPLEXITY: O(N).
SPACE COMPLEXITY: O(1).
*/

class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n <= 1) return 0;

        int buy = -prices[0];
        int sell = 0;
        int rest = 0;

        for (int i = 1; i < n; i++) {
            int prevBuy = buy;
            buy = Math.max(buy, rest - prices[i]);
            rest = Math.max(rest, sell);
            sell = prevBuy + prices[i];
        }

        return Math.max(sell, rest);
    }
}
