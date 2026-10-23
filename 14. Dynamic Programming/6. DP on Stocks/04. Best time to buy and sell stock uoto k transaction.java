/*
QUESTION:
You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k.
Find the maximum profit you can achieve. You may complete at most k transactions: i.e. you may buy at most k times and sell at most k times.
Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

Example:
Input: k = 2, prices = [2, 4, 1]
Output: 2
Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4 - 2 = 2.

APPROACH:
- This problem can be solved using dynamic programming.
- We need to keep track of the number of transactions completed so far (cap) and whether we are holding a stock or not on each day.
- Define a 3D DP array `dp` where dp[i][hold][cap] represents the maximum profit achievable starting from day i while holding or not holding a stock and having completed cap transactions.
- The base case is dp[n][0][0] = dp[n][0][1] = ... = dp[n][0][k] = dp[n][1][0] = ... = dp[n][1][k] = 0, where n is the number of days and k is the given limit of transactions.
- Iterate through each day i in reverse order.
- If holding a stock, we can either sell the stock or continue holding.
- If not holding a stock, we can either buy the stock or continue not holding.
- Return dp[0][0][0], which represents the maximum profit achievable starting from day 0 while not holding a stock and having completed 0 transactions.

COMPLEXITY ANALYSIS:
- The time complexity of this approach is O(n * k), where n is the number of days and k is the given limit of transactions.
- The space complexity is O(n * k) due to the dp array.
- Overall, the algorithm runs in O(n * k) time and O(n * k) space.

CODE:
*/

import java.io.*;
import java.util.*;
import java.util.Arrays;

// CODE:-
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
