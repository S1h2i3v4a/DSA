/*
QUESTION:
Best Time to Buy and Sell Stock with Transaction Fee.

APPROACH:
- Space-Optimized Variables:
- `aheadBuy` and `aheadNotBuy`.
- `currBuy = max(-prices[i] + aheadNotBuy, aheadBuy)`.
- `currNotBuy = max(prices[i] - fee + aheadBuy, aheadNotBuy)`.

TIME COMPLEXITY: O(N).
SPACE COMPLEXITY: O(1).
*/

class Solution {
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int aheadBuy = 0;
        int aheadNotBuy = 0;

        for (int i = n - 1; i >= 0; i--) {
            int currBuy = Math.max(-prices[i] + aheadNotBuy, aheadBuy);
            int currNotBuy = Math.max(prices[i] - fee + aheadBuy, aheadNotBuy);
            aheadBuy = currBuy;
            aheadNotBuy = currNotBuy;
        }

        return aheadBuy;
    }
}
