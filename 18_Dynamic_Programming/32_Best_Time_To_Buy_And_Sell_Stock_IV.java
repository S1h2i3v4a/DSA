/**
 * Problem Name: Best Time to Buy and Sell Stock IV (At Most K Transactions)
 * Platform: LeetCode (LC 188) / GeeksforGeeks
 * Difficulty: Hard
 *
 * Time Complexity: O(N * K) where N is number of days and K is transaction limit
 * Space Complexity:
 *   - Memoization: O(N * 2 * K) table + O(N) recursion stack
 *   - Tabulation: O(N * 2 * K) table
 *   - Space-Optimized DP: O(K) auxiliary space
 *
 * Approach:
 *   - Generalized version of Stock III where at most K transactions are allowed.
 *   - State: dp[day][canBuy][cap] where cap ranges from 0 to K.
 *   - If K >= N / 2, it reduces to Stock II (infinite transactions) since we can trade every day.
 *   - Recurrence:
 *     canBuy == 1: max(-prices[i] + ahead[0][cap], ahead[1][cap])
 *     canBuy == 0: max(prices[i] + ahead[1][cap - 1], ahead[0][cap])
 *   - Space-Optimized DP:
 *     Only requires ahead[2][K + 1] and curr[2][K + 1], reducing memory to O(K).
 */

import java.util.Arrays;

class BestTimeToBuyAndSellStockIV {

    public static int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n == 0 || k == 0) return 0;

        // If k is large, equivalent to unlimited transactions
        if (k >= n / 2) {
            int maxProfit = 0;
            for (int i = 1; i < n; i++) {
                if (prices[i] > prices[i - 1]) {
                    maxProfit += prices[i] - prices[i - 1];
                }
            }
            return maxProfit;
        }

        int[][] ahead = new int[2][k + 1];

        for (int i = n - 1; i >= 0; i--) {
            int[][] curr = new int[2][k + 1];
            for (int canBuy = 0; canBuy <= 1; canBuy++) {
                for (int cap = 1; cap <= k; cap++) {
                    if (canBuy == 1) {
                        curr[canBuy][cap] = Math.max(-prices[i] + ahead[0][cap], ahead[1][cap]);
                    } else {
                        curr[canBuy][cap] = Math.max(prices[i] + ahead[1][cap - 1], ahead[0][cap]);
                    }
                }
            }
            ahead = curr;
        }

        return ahead[1][k];
    }

    public static void main(String[] args) {
        // Test Case 1: k = 2, prices = [2,4,1] -> Expected: 2 (Buy @2, sell @4)
        int[] p1 = {2, 4, 1};
        System.out.println("Test 1 (k=2, prices=[2,4,1]): " + maxProfit(2, p1)); // 2

        // Test Case 2: k = 2, prices = [3,2,6,5,0,3] -> Expected: 7 (Buy @2 sell @6 + buy @0 sell @3 = 4 + 3 = 7)
        int[] p2 = {3, 2, 6, 5, 0, 3};
        System.out.println("Test 2 (k=2, prices=[3,2,6,5,0,3]): " + maxProfit(2, p2)); // 7
    }
}
