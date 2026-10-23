/**
 * Problem Name: Best Time to Buy and Sell Stock with Transaction Fee
 * Platform: LeetCode (LC 714) / GeeksforGeeks
 * Difficulty: Medium
 *
 * Time Complexity: O(N) where N is number of days
 * Space Complexity: O(1) auxiliary variables
 *
 * Approach:
 *   - You may complete as many transactions as you like, but you need to pay the transaction fee
 *     for each transaction (typically deducted upon selling).
 *   - Recurrence:
 *     canBuy == 1: max(-prices[i] + aheadNotBuy, aheadBuy)
 *     canBuy == 0: max(prices[i] - fee + aheadBuy, aheadNotBuy)
 *   - Space Optimization:
 *     Two rolling variables `aheadBuy` and `aheadNotBuy` provide an optimal O(1) space solution.
 */

class BestTimeToBuyAndSellStockWithTransactionFee {

    public static int maxProfit(int[] prices, int fee) {
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

    public static void main(String[] args) {
        // Test Case 1: prices = [1,3,2,8,4,9], fee = 2 -> Expected: 8
        int[] p1 = {1, 3, 2, 8, 4, 9};
        System.out.println("Test 1: " + maxProfit(p1, 2)); // 8

        // Test Case 2: prices = [1,3,7,5,10,3], fee = 3 -> Expected: 6
        int[] p2 = {1, 3, 7, 5, 10, 3};
        System.out.println("Test 2: " + maxProfit(p2, 3)); // 6
    }
}
