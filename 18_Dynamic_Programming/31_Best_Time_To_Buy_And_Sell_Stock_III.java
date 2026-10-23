/**
 * Problem Name: Best Time to Buy and Sell Stock III (At Most 2 Transactions)
 * Platform: LeetCode (LC 123) / GeeksforGeeks
 * Difficulty: Hard
 *
 * Time Complexity: O(N * 2 * 3) = O(N) where N is number of days
 * Space Complexity:
 *   - Memoization: O(N * 2 * 3) table + O(N) recursion stack
 *   - Tabulation: O(N * 2 * 3) table
 *   - Space-Optimized DP: O(1) auxiliary space (2x3 arrays)
 *
 * Approach:
 *   - At most 2 transactions are allowed. A transaction completes upon selling.
 *   - State: f(i, canBuy, cap) where `cap` is the remaining transaction budget (2, 1, 0).
 *   - Recurrence:
 *     If cap == 0: return 0 (no more transactions permitted).
 *     If canBuy == 1:
 *       max(-prices[i] + f(i + 1, 0, cap), f(i + 1, 1, cap))
 *     If canBuy == 0:
 *       max(prices[i] + f(i + 1, 1, cap - 1), f(i + 1, 0, cap))  // cap decreases upon selling!
 *   - Space-Optimized DP:
 *     Only requires a 2x3 state from day i + 1, achieving O(1) extra space.
 */

import java.util.Arrays;

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
