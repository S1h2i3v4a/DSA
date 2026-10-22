/**
 * Problem Name: Coin Change II (Number of Ways / Combinations)
 * Platform: LeetCode (LC 518) / GeeksforGeeks
 * Difficulty: Medium
 *
 * Time Complexity: O(N * amount) where N is the number of coin denominations
 * Space Complexity:
 *   - Memoization: O(N * amount) table + O(N) recursion stack
 *   - Tabulation: O(N * amount) table
 *   - Space-Optimized DP: O(amount) 1D array
 *
 * Approach:
 *   - Return the number of combinations that make up `amount` using given `coins`.
 *   - Infinite supply of each coin denomination (Unbounded Knapsack variant).
 *   - Combinations only: order does NOT matter ([1, 2] is identical to [2, 1]).
 *   - Recurrence:
 *     f(i, target) = notPick + pick
 *       notPick = f(i - 1, target)
 *       pick = (coins[i] <= target) ? f(i, target - coins[i]) : 0  (stay at index i)
 *     Base Case:
 *       At index 0: if target % coins[0] == 0 return 1; else return 0.
 *   - Space Optimization:
 *     Using a 1D array of size amount + 1:
 *     dp[0] = 1 (1 way to make amount 0).
 *     For each coin, iterate forwards `a` from coin to amount:
 *       dp[a] += dp[a - coin]
 */

import java.util.Arrays;

class CoinChangeII {

    // ==========================================
    // Approach 1: Top-Down DP (Memoization)
    // ==========================================
    public static int changeMemo(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n][amount + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return memoHelper(n - 1, amount, coins, dp);
    }

    private static int memoHelper(int index, int target, int[] coins, int[][] dp) {
        if (index == 0) {
            return (target % coins[0] == 0) ? 1 : 0;
        }

        if (dp[index][target] != -1) {
            return dp[index][target];
        }

        int notPick = memoHelper(index - 1, target, coins, dp);
        int pick = 0;
        if (coins[index] <= target) {
            pick = memoHelper(index, target - coins[index], coins, dp);
        }

        return dp[index][target] = notPick + pick;
    }

    // ==========================================
    // Approach 2: Bottom-Up DP (Tabulation)
    // ==========================================
    public static int changeTabulation(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n][amount + 1];

        // Base case for index 0
        for (int t = 0; t <= amount; t++) {
            if (t % coins[0] == 0) {
                dp[0][t] = 1;
            }
        }

        for (int i = 1; i < n; i++) {
            for (int t = 0; t <= amount; t++) {
                int notPick = dp[i - 1][t];
                int pick = 0;
                if (coins[i] <= t) {
                    pick = dp[i][t - coins[i]];
                }
                dp[i][t] = notPick + pick;
            }
        }

        return dp[n - 1][amount];
    }

    // ==========================================
    // Approach 3: Space-Optimized DP - O(amount) Space
    // ==========================================
    public static int changeSpaceOptimized(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1; // 1 way to form amount 0: empty set of coins

        for (int coin : coins) {
            // Forward iteration enables multiple selections of the same coin denomination
            for (int a = coin; a <= amount; a++) {
                dp[a] += dp[a - coin];
            }
        }

        return dp[amount];
    }

    public static void main(String[] args) {
        // Test Case 1:
        // amount = 5, coins = [1, 2, 5] -> Expected: 4
        // Combinations: [5], [2,2,1], [2,1,1,1], [1,1,1,1,1]
        int amount1 = 5;
        int[] coins1 = {1, 2, 5};
        System.out.println("Test 1 (amount=5, coins=[1,2,5]):");
        System.out.println("  Memoization:     " + changeMemo(amount1, coins1)); // 4
        System.out.println("  Tabulation:      " + changeTabulation(amount1, coins1)); // 4
        System.out.println("  Space-Optimized: " + changeSpaceOptimized(amount1, coins1)); // 4

        // Test Case 2:
        // amount = 3, coins = [2] -> Expected: 0
        int amount2 = 3;
        int[] coins2 = {2};
        System.out.println("\nTest 2 (amount=3, coins=[2]):");
        System.out.println("  Memoization:     " + changeMemo(amount2, coins2)); // 0
        System.out.println("  Tabulation:      " + changeTabulation(amount2, coins2)); // 0
        System.out.println("  Space-Optimized: " + changeSpaceOptimized(amount2, coins2)); // 0

        // Test Case 3:
        // amount = 10, coins = [10] -> Expected: 1
        int amount3 = 10;
        int[] coins3 = {10};
        System.out.println("\nTest 3 (amount=10, coins=[10]):");
        System.out.println("  Space-Optimized: " + changeSpaceOptimized(amount3, coins3)); // 1
    }
}
