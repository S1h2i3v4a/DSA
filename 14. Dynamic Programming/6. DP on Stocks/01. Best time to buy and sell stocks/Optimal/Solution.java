/*
QUESTION:
Best Time to Buy and Sell Stock (Single Transaction).

APPROACH:
- Maintain `minPrice` seen so far.
- At each day, profit = prices[i] - minPrice.
- Update maxProfit.

TIME COMPLEXITY: O(N) single pass.
SPACE COMPLEXITY: O(1) constant auxiliary space.
*/

class Solution {
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int price : prices) {
            minPrice = Math.min(minPrice, price);
            maxProfit = Math.max(maxProfit, price - minPrice);
        }

        return maxProfit;
    }
}
