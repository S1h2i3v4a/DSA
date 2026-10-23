/*
QUESTION:
You are given an array prices where prices[i] is the price of a given stock on the ith day.
Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:
- After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

Example:
Input: prices = [1, 2, 3, 0, 2]
Output: 3
Explanation: transactions = [buy, sell, cooldown, buy, sell]

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
class BestTimeToBuyAndSellStockWithCooldown {

    public static int maxProfitSpaceOptimized(int[] prices) {
        int n = prices.length;
        if (n <= 1) return 0;

        int[] front2 = new int[2]; // Day i + 2
        int[] front1 = new int[2]; // Day i + 1
        int[] curr = new int[2];   // Day i

        for (int i = n - 1; i >= 0; i--) {
            // canBuy == 1
            curr[1] = Math.max(-prices[i] + front1[0], front1[1]);
            // canBuy == 0: selling transitions to i + 2
            curr[0] = Math.max(prices[i] + front2[1], front1[0]);

            front2[0] = front1[0];
            front2[1] = front1[1];
            front1[0] = curr[0];
            front1[1] = curr[1];
        }

        return curr[1];
    }

    public static void main(String[] args) {
        // Test Case 1: prices = [1,2,3,0,2] -> Expected: 3 ([buy, sell, cooldown, buy, sell])
        int[] p1 = {1, 2, 3, 0, 2};
        System.out.println("Test 1: " + maxProfitSpaceOptimized(p1)); // 3

        // Test Case 2: prices = [1] -> Expected: 0
        int[] p2 = {1};
        System.out.println("Test 2: " + maxProfitSpaceOptimized(p2)); // 0
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
