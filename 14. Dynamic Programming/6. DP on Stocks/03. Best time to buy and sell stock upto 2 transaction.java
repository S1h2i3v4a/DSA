/*
QUESTION:
You are given an array prices where prices[i] is the price of a given stock on the ith day.
Find the maximum profit you can achieve. You may complete at most two transactions.
Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

Example:
Input: prices = [3, 3, 5, 0, 0, 3, 1, 4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3 - 0 = 3.
Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4 - 1 = 3.

APPROACH:
- This problem can be solved using dynamic programming.
- We need to keep track of the number of transactions completed so far (cap) and whether we are holding a stock or not on each day.
- Define a 3D DP array `dp` where dp[i][hold][cap] represents the maximum profit achievable starting from day i while holding or not holding a stock and having completed cap transactions.
- The base case is dp[n][0][0] = dp[n][0][1] = dp[n][0][2] = dp[n][1][0] = dp[n][1][1] = dp[n][1][2] = 0, where n is the number of days.
- Iterate through each day i in reverse order.
- If holding a stock, we can either sell the stock or continue holding.
- If not holding a stock, we can either buy the stock or continue not holding.
- Return dp[0][0][0], which represents the maximum profit achievable starting from day 0 while not holding a stock and having completed 0 transactions.

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
class BestTimeToBuyAndSellStockIII {

    // ==========================================
    // Approach 1: Top-Down DP (Memoization)
    // ==========================================
    public static int maxProfitMemo(int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n][2][3];
        for (int[][] mat : dp) {
            for (int[] row : mat) {
                Arrays.fill(row, -1);
            }
        }
        return memoHelper(0, 1, 2, prices, dp, n);
    }

    private static int memoHelper(int i, int canBuy, int cap, int[] prices, int[][][] dp, int n) {
        if (i == n || cap == 0) return 0;
        if (dp[i][canBuy][cap] != -1) return dp[i][canBuy][cap];

        int profit;
        if (canBuy == 1) {
            int buy = -prices[i] + memoHelper(i + 1, 0, cap, prices, dp, n);
            int skip = memoHelper(i + 1, 1, cap, prices, dp, n);
            profit = Math.max(buy, skip);
        } else {
            int sell = prices[i] + memoHelper(i + 1, 1, cap - 1, prices, dp, n);
            int skip = memoHelper(i + 1, 0, cap, prices, dp, n);
            profit = Math.max(sell, skip);
        }

        return dp[i][canBuy][cap] = profit;
    }

    // ==========================================
    // Approach 2: Bottom-Up DP (Tabulation)
    // ==========================================
    public static int maxProfitTabulation(int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n + 1][2][3];

        for (int i = n - 1; i >= 0; i--) {
            for (int canBuy = 0; canBuy <= 1; canBuy++) {
                for (int cap = 1; cap <= 2; cap++) {
                    if (canBuy == 1) {
                        dp[i][canBuy][cap] = Math.max(-prices[i] + dp[i + 1][0][cap], dp[i + 1][1][cap]);
                    } else {
                        dp[i][canBuy][cap] = Math.max(prices[i] + dp[i + 1][1][cap - 1], dp[i + 1][0][cap]);
                    }
                }
            }
        }

        return dp[0][1][2];
    }

    // ==========================================
    // Approach 3: Space-Optimized DP - O(1) Space
    // ==========================================
    public static int maxProfitSpaceOptimized(int[] prices) {
        int n = prices.length;
        int[][] ahead = new int[2][3];

        for (int i = n - 1; i >= 0; i--) {
            int[][] curr = new int[2][3];
            for (int canBuy = 0; canBuy <= 1; canBuy++) {
                for (int cap = 1; cap <= 2; cap++) {
                    if (canBuy == 1) {
                        curr[canBuy][cap] = Math.max(-prices[i] + ahead[0][cap], ahead[1][cap]);
                    } else {
                        curr[canBuy][cap] = Math.max(prices[i] + ahead[1][cap - 1], ahead[0][cap]);
                    }
                }
            }
            ahead = curr;
        }

        return ahead[1][2];
    }

    public static void main(String[] args) {
        // Test Case 1: prices = [3,3,5,0,0,3,1,4] -> Expected: 6 (Buy @0, sell @3 + buy @1, sell @4 = 3 + 3 = 6)
        int[] p1 = {3, 3, 5, 0, 0, 3, 1, 4};
        System.out.println("Test 1: " + maxProfitSpaceOptimized(p1)); // 6

        // Test Case 2: prices = [1,2,3,4,5] -> Expected: 4 (Buy @1, sell @5 = 4)
        int[] p2 = {1, 2, 3, 4, 5};
        System.out.println("Test 2: " + maxProfitSpaceOptimized(p2)); // 4

        // Test Case 3: prices = [7,6,4,3,1] -> Expected: 0
        int[] p3 = {7, 6, 4, 3, 1};
        System.out.println("Test 3: " + maxProfitSpaceOptimized(p3)); // 0
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
