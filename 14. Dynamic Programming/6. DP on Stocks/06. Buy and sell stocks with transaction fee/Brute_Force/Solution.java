/*
QUESTION:
Find maximum profit with unlimited transactions and a transaction fee per transaction.

APPROACH:
- Pure recursion: deduct fee when completing a transaction (selling).

TIME COMPLEXITY: O(2^N).
SPACE COMPLEXITY: O(N) recursion stack.
*/

class Solution {
    public int maxProfit(int[] prices, int fee) {
        return solve(0, 1, prices, fee);
    }

    private int solve(int i, int canBuy, int[] prices, int fee) {
        if (i == prices.length) return 0;

        if (canBuy == 1) {
            int buy = -prices[i] + solve(i + 1, 0, prices, fee);
            int skip = solve(i + 1, 1, prices, fee);
            return Math.max(buy, skip);
        } else {
            int sell = prices[i] - fee + solve(i + 1, 1, prices, fee);
            int skip = solve(i + 1, 0, prices, fee);
            return Math.max(sell, skip);
        }
    }
}
