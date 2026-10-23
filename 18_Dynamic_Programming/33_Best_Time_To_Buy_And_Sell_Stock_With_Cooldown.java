/**
 * Problem Name: Best Time to Buy and Sell Stock with Cooldown
 * Platform: LeetCode (LC 309) / GeeksforGeeks
 * Difficulty: Medium
 *
 * Time Complexity: O(N) where N is number of days
 * Space Complexity:
 *   - Memoization: O(N * 2) table + O(N) recursion stack
 *   - Tabulation: O(N * 2) table
 *   - Space-Optimized DP: O(1) auxiliary variables
 *
 * Approach:
 *   - Infinite transactions permitted, with a mandatory 1-day cooldown after selling:
 *     After selling on day `i`, you cannot buy stock on day `i + 1` (can only buy on `i + 2` or later).
 *   - Recurrence:
 *     If canBuy == 1:
 *       max(-prices[i] + f(i + 1, 0), f(i + 1, 1))
 *     If canBuy == 0:
 *       max(prices[i] + f(i + 2, 1), f(i + 1, 0))  // Cooldown skips day i + 1 upon selling!
 *   - Space Optimization:
 *     Day i only depends on day i + 1 and day i + 2 states.
 *     We can maintain 3 sets of buy/notBuy states, giving O(1) space.
 */

import java.util.Arrays;

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
