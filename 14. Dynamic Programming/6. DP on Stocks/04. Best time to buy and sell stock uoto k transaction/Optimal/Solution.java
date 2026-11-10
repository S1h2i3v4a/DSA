/*
QUESTION:
Maximum profit with at most k transactions.

APPROACH:
- Space-Optimized Tabulation:
- If k >= n / 2, unlimited transactions can be made (sum all positive price differences).
- Otherwise, maintain two arrays `ahead[2][k + 1]` and `curr[2][k + 1]`.

TIME COMPLEXITY: O(N * K).
SPACE COMPLEXITY: O(K) space.
*/

class Solution {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n == 0 || k == 0) return 0;

        if (k >= n / 2) {
            int maxP = 0;
            for (int i = 1; i < n; i++) {
                if (prices[i] > prices[i - 1]) maxP += prices[i] - prices[i - 1];
            }
            return maxP;
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
}
