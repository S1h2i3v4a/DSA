/*
QUESTION:-
Given an array prices where prices[i] is the price of a given stock on the ith day. You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.

Example:
Input: prices = [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6 - 1 = 5.
*/

/*
APPROACH:-
Single Pass: Maintain minimum price seen so far (minPrice). On each day, profit = prices[i] - minPrice. Update maxProfit and minPrice dynamically.
*/

import java.util.*;
import java.io.*;

// CODE:-
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
