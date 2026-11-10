/*
QUESTION:
Find the maximum profit you can achieve with at most two transactions.

APPROACH:
- Pure recursion: state (i, canBuy, cap) where cap is remaining transactions allowed (starts at 2).

TIME COMPLEXITY: O(2^N).
SPACE COMPLEXITY: O(N) recursion stack.
*/

class Solution {
    public int maxProfit(int[] prices) {
        return solve(0, 1, 2, prices);
    }

    private int solve(int i, int canBuy, int cap, int[] prices) {
        if (i == prices.length || cap == 0) return 0;

        if (canBuy == 1) {
            int buy = -prices[i] + solve(i + 1, 0, cap, prices);
            int skip = solve(i + 1, 1, cap, prices);
            return Math.max(buy, skip);
        } else {
            int sell = prices[i] + solve(i + 1, 1, cap - 1, prices);
            int skip = solve(i + 1, 0, cap, prices);
            return Math.max(sell, skip);
        }
    }
}
