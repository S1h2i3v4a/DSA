/*
QUESTION:
You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time.
However, you can buy it then immediately sell it on the same day.
Find and return the maximum profit you can achieve.

Example:
Input: prices = [7, 1, 5, 3, 6, 4]
Output: 7
Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5 - 1 = 4.
Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6 - 3 = 3.
Total profit is 4 + 3 = 7.

APPROACH:
- This problem can be solved using dynamic programming.
- We need to keep track of whether we are holding a stock or not on each day.
- Define a 2D DP array `dp` where dp[i][hold] represents the maximum profit achievable starting from day i while holding or not holding a stock.
- The base case is dp[n][0] = dp[n][1] = 0, where n is the number of days.
- Iterate through each day i in reverse order.
- If holding a stock, we can either sell the stock or continue holding.
- If not holding a stock, we can either buy the stock or continue not holding.
- Return dp[0][0], which represents the maximum profit achievable starting from day 0 while not holding a stock.

COMPLEXITY ANALYSIS:
- The time complexity of this approach is O(n), where n is the number of days.
- The space complexity is O(n) due to the dp array.
- Overall, the algorithm runs in O(n) time and O(n) space.

CODE:
*/

import java.io.*;
import java.util.*;
import java.util.Arrays;

// CODE:-
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
