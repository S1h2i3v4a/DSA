/**
 * Problem Name: Coin Change (Minimum Coins)
 * Platform: LeetCode (LC 322) / GeeksforGeeks
 * Difficulty: Medium
 *
 * Time Complexity: O(N * amount) where N is number of coin denominations
 * Space Complexity:
 *   - Memoization: O(N * amount) table + O(amount) recursion stack
 *   - Tabulation: O(N * amount) table
 *   - Space-Optimized DP: O(amount) 1D array
 *
 * Approach:
 *   - Find the minimum number of coins needed to make up a given amount.
 *   - Each coin denomination has an infinite supply (Unbounded Knapsack variation).
 *   - If the amount cannot be formed, return -1.
 *   - Recurrence:
 *     At index `i` with current target `T`:
 *       Option 1 (Exclude coin): f(i - 1, T)
 *       Option 2 (Include coin, staying at index i): 1 + f(i, T - coins[i])
 *       f(i, T) = min(Option 1, Option 2)
 *   - Space Optimization:
 *     Using a 1D array of size `amount + 1` initialized to infinity (amount + 1),
 *     with dp[0] = 0.
 *     For each coin, iterate forwards `a` from coin to amount:
 *       dp[a] = min(dp[a], 1 + dp[a - coin])
 */

import java.util.Arrays;

class CoinChange {

    private static final int INF = (int) 1e9;

    // ==========================================
    // Approach 1: Top-Down DP (Memoization)
    // ==========================================
    public static int coinChangeMemo(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n][amount + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        int result = memoHelper(n - 1, amount, coins, dp);
        return (result >= INF) ? -1 : result;
    }

    private static int memoHelper(int index, int T, int[] coins, int[][] dp) {
        if (index == 0) {
            if (T % coins[0] == 0) return T / coins[0];
            return INF;
        }

        if (dp[index][T] != -1) {
            return dp[index][T];
        }

        int notPick = memoHelper(index - 1, T, coins, dp);
        int pick = INF;
        if (coins[index] <= T) {
            pick = 1 + memoHelper(index, T - coins[index], coins, dp);
        }

        return dp[index][T] = Math.min(notPick, pick);
    }

    // ==========================================
    // Approach 2: Bottom-Up DP (Tabulation)
    // ==========================================
    public static int coinChangeTabulation(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n][amount + 1];

        // Base case for index 0
        for (int t = 0; t <= amount; t++) {
            if (t % coins[0] == 0) {
                dp[0][t] = t / coins[0];
            } else {
                dp[0][t] = INF;
            }
        }

        for (int i = 1; i < n; i++) {
            for (int t = 0; t <= amount; t++) {
                int notPick = dp[i - 1][t];
                int pick = INF;
                if (coins[i] <= t) {
                    pick = 1 + dp[i][t - coins[i]];
                }
                dp[i][t] = Math.min(notPick, pick);
            }
        }

        int ans = dp[n - 1][amount];
        return (ans >= INF) ? -1 : ans;
    }

    // ==========================================
    // Approach 3: Space-Optimized DP - O(amount) Space
    // ==========================================
    public static int coinChangeSpaceOptimized(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int coin : coins) {
            for (int a = coin; a <= amount; a++) {
                dp[a] = Math.min(dp[a], 1 + dp[a - coin]);
            }
        }

        return (dp[amount] > amount) ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        // Test Case 1:
        // coins = [1, 2, 5], amount = 11 -> Expected: 3 (5 + 5 + 1)
        int[] coins1 = {1, 2, 5};
        int amount1 = 11;
        System.out.println("Test 1 (coins=[1,2,5], amount=11):");
        System.out.println("  Memoization:     " + coinChangeMemo(coins1, amount1)); // 3
        System.out.println("  Tabulation:      " + coinChangeTabulation(coins1, amount1)); // 3
        System.out.println("  Space-Optimized: " + coinChangeSpaceOptimized(coins1, amount1)); // 3

        // Test Case 2:
        // coins = [2], amount = 3 -> Expected: -1
        int[] coins2 = {2};
        int amount2 = 3;
        System.out.println("\nTest 2 (coins=[2], amount=3):");
        System.out.println("  Memoization:     " + coinChangeMemo(coins2, amount2)); // -1
        System.out.println("  Tabulation:      " + coinChangeTabulation(coins2, amount2)); // -1
        System.out.println("  Space-Optimized: " + coinChangeSpaceOptimized(coins2, amount2)); // -1

        // Test Case 3:
        // coins = [1], amount = 0 -> Expected: 0
        int[] coins3 = {1};
        int amount3 = 0;
        System.out.println("\nTest 3 (coins=[1], amount=0):");
        System.out.println("  Space-Optimized: " + coinChangeSpaceOptimized(coins3, amount3)); // 0
    }
}
