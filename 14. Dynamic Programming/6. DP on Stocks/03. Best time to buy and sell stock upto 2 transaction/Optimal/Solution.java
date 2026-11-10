/*
QUESTION:
Maximum profit with at most two transactions.

APPROACH:
- Space-Optimized Tabulation:
- Maintain two 2D arrays `ahead[2][3]` and `curr[2][3]`.

TIME COMPLEXITY: O(N).
SPACE COMPLEXITY: O(1) constant auxiliary space.
*/

class Solution {
    public int maxProfit(int[] prices) {
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
}
