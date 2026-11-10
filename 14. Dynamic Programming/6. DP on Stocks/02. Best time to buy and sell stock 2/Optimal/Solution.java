/*
QUESTION:
Best Time to Buy and Sell Stock II (Unlimited Transactions).

APPROACH:
- Space-Optimized Tabulation:
- Keep two variables `aheadBuy` and `aheadNotBuy`.
- Iterate backwards from n - 1 down to 0.

TIME COMPLEXITY: O(N) single pass.
SPACE COMPLEXITY: O(1) constant auxiliary space.
*/

class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int aheadBuy = 0, aheadNotBuy = 0;

        for (int i = n - 1; i >= 0; i--) {
            int currBuy = Math.max(-prices[i] + aheadNotBuy, aheadBuy);
            int currNotBuy = Math.max(prices[i] + aheadBuy, aheadNotBuy);
            aheadBuy = currBuy;
            aheadNotBuy = currNotBuy;
        }

        return aheadBuy;
    }
}
