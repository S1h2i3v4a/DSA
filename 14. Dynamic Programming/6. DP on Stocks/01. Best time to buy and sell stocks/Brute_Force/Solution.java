/*
QUESTION:
You are given an array prices where prices[i] is the price of a given stock on the ith day.
Maximize profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.

APPROACH:
- Check every pair (i, j) with j > i and compute prices[j] - prices[i].
- Return the maximum profit.

TIME COMPLEXITY: O(N^2).
SPACE COMPLEXITY: O(1).
*/

class Solution {
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int n = prices.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                maxProfit = Math.max(maxProfit, prices[j] - prices[i]);
            }
        }
        return maxProfit;
    }
}
