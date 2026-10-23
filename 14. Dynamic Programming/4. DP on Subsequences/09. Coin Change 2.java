/*
QUESTION:
Given an integer array 'coins' representing coins of different 
denominations and an integer 'amount' representing a total amount 
of money, the task is to return the number of combinations that 
make up that amount using the given coins. If that amount of money 
cannot be made up by any combination of the coins, return 0.

Example:
Input: amount = 5, coins = [1, 2, 5]
Output: 4
Explanation: There are four ways to make up the amount:
5 = 5
5 = 2 + 2 + 1
5 = 2 + 1 + 1 + 1
5 = 1 + 1 + 1 + 1 + 1

APPROACH:
- This problem can be solved using Dynamic Programming (DP).
- Initialize a 2D DP array 'dp' where dp[i][j] represents the number 
  of combinations to make up amount 'j' using the first 'i' coins.
- Base case:
  - dp[i][0] = 1 for all 'i' (one way to make 0 amount with any coin)
- For each coin 'i' and each possible amount 'j':
  - dp[i][j] = dp[i-1][j] + dp[i][j-coins[i]]
- The required answer will be dp[n][amount] where 'n' is the number 
  of coins and 'amount' is the given total amount.

COMPLEXITY ANALYSIS:
- The time complexity of the solution is O(N * amount), where 'N' is 
  the number of coins and 'amount' is the given total amount.
- The space complexity is O(N * amount) for the dp array.

CODE:
*/

import java.io.*;
import java.util.*;
import java.util.Arrays;

// CODE:-
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
