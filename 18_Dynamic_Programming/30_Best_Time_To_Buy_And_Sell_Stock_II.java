/**
 * Problem Name: Best Time to Buy and Sell Stock II (Infinite Transactions)
 * Platform: LeetCode (LC 122) / GeeksforGeeks
 * Difficulty: Medium
 *
 * Time Complexity: O(N) where N is the number of days
 * Space Complexity:
 *   - Memoization: O(N * 2) table + O(N) recursion stack
 *   - Tabulation: O(N * 2) table
 *   - Space-Optimized DP: O(1) auxiliary variables
 *
 * Approach:
 *   - You may complete as many transactions as you like (buy one and sell one share multiple times).
 *   - Constraint: Cannot hold more than 1 share at any time (must sell before buying again).
 *   - State Definition:
 *     f(i, canBuy):
 *       If canBuy == 1:
 *         Choice 1 (Buy): -prices[i] + f(i + 1, 0)
 *         Choice 2 (Skip): f(i + 1, 1)
 *       If canBuy == 0:
 *         Choice 1 (Sell): prices[i] + f(i + 1, 1)
 *         Choice 2 (Skip): f(i + 1, 0)
 *   - Space Optimization:
 *     Day `i` only depends on Day `i + 1`. We only need two variables for ahead state:
 *     aheadBuy and aheadNotBuy, yielding O(1) space.
 */

import java.util.Arrays;

class BestTimeToBuyAndSellStockII {

    // ==========================================
    // Approach 1: Top-Down DP (Memoization)
    // ==========================================
    public static int maxProfitMemo(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int[] row : dp) Arrays.fill(row, -1);
        return memoHelper(0, 1, prices, dp, n);
    }

    private static int memoHelper(int i, int canBuy, int[] prices, int[][] dp, int n) {
        if (i == n) return 0;
        if (dp[i][canBuy] != -1) return dp[i][canBuy];

        int profit;
        if (canBuy == 1) {
            int buy = -prices[i] + memoHelper(i + 1, 0, prices, dp, n);
            int skip = memoHelper(i + 1, 1, prices, dp, n);
            profit = Math.max(buy, skip);
        } else {
            int sell = prices[i] + memoHelper(i + 1, 1, prices, dp, n);
            int skip = memoHelper(i + 1, 0, prices, dp, n);
            profit = Math.max(sell, skip);
        }

        return dp[i][canBuy] = profit;
    }

    // ==========================================
    // Approach 2: Bottom-Up DP (Tabulation)
    // ==========================================
    public static int maxProfitTabulation(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n + 1][2];

        // Base case: day n profit is 0
        dp[n][0] = dp[n][1] = 0;

        for (int i = n - 1; i >= 0; i--) {
            for (int canBuy = 0; canBuy <= 1; canBuy++) {
                if (canBuy == 1) {
                    dp[i][canBuy] = Math.max(-prices[i] + dp[i + 1][0], dp[i + 1][1]);
                } else {
                    dp[i][canBuy] = Math.max(prices[i] + dp[i + 1][1], dp[i + 1][0]);
                }
            }
        }

        return dp[0][1];
    }

    // ==========================================
    // Approach 3: Space-Optimized DP - O(1) Space
    // ==========================================
    public static int maxProfitSpaceOptimized(int[] prices) {
        int n = prices.length;
        int aheadNotBuy = 0, aheadBuy = 0;

        for (int i = n - 1; i >= 0; i--) {
            int currBuy = Math.max(-prices[i] + aheadNotBuy, aheadBuy);
            int currNotBuy = Math.max(prices[i] + aheadBuy, aheadNotBuy);

            aheadBuy = currBuy;
            aheadNotBuy = currNotBuy;
        }

        return aheadBuy;
    }

    public static void main(String[] args) {
        // Test Case 1: prices = [7,1,5,3,6,4] -> Expected: 7 (Buy day 2 @1, sell @5 + buy @3, sell @6 = 4 + 3 = 7)
        int[] p1 = {7, 1, 5, 3, 6, 4};
        System.out.println("Test 1: " + maxProfitSpaceOptimized(p1)); // 7

        // Test Case 2: prices = [1,2,3,4,5] -> Expected: 4
        int[] p2 = {1, 2, 3, 4, 5};
        System.out.println("Test 2: " + maxProfitSpaceOptimized(p2)); // 4

        // Test Case 3: prices = [7,6,4,3,1] -> Expected: 0
        int[] p3 = {7, 6, 4, 3, 1};
        System.out.println("Test 3: " + maxProfitSpaceOptimized(p3)); // 0
    }
}
