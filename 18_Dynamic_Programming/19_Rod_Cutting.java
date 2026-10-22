/**
 * Problem Name: Rod Cutting Problem
 * Platform: GeeksforGeeks / Standard Data Structures & Algorithms
 * Difficulty: Medium
 *
 * Time Complexity: O(N^2) where N is the length of the rod
 * Space Complexity:
 *   - Memoization: O(N^2) table + O(N) recursion stack
 *   - Tabulation: O(N^2) table
 *   - Space-Optimized DP: O(N) 1D array
 *
 * Approach:
 *   - Given a rod of length N and an array `price` where price[i] is the price of a rod piece
 *     of length i + 1.
 *   - We can make any number of cuts and sell the resulting pieces to maximize revenue.
 *   - Direct equivalence to Unbounded Knapsack:
 *     - Knapsack Capacity = N (rod length)
 *     - Item Weights = 1, 2, 3, ..., N (piece lengths)
 *     - Item Values = price[0], price[1], ..., price[N-1]
 *     - Item Quantity = Infinite / Unbounded (can cut multiple pieces of the same length)
 *   - Recurrence:
 *     dp[len] = max_{1 <= cut <= len} (price[cut - 1] + dp[len - cut])
 *   - We provide:
 *     1. Top-Down DP with Memoization
 *     2. Bottom-Up DP with Tabulation
 *     3. Space-Optimized DP using an O(N) 1D array.
 */

import java.util.Arrays;

class RodCutting {

    // ==========================================
    // Approach 1: Top-Down DP (Memoization)
    // ==========================================
    public static int cutRodMemo(int[] price, int n) {
        int[][] dp = new int[n][n + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return memoHelper(n - 1, n, price, dp);
    }

    private static int memoHelper(int index, int remainingLength, int[] price, int[][] dp) {
        if (index == 0) {
            return remainingLength * price[0]; // Pieces of length 1
        }

        if (dp[index][remainingLength] != -1) {
            return dp[index][remainingLength];
        }

        int notCut = memoHelper(index - 1, remainingLength, price, dp);
        int cut = 0;
        int rodPieceLen = index + 1;
        if (rodPieceLen <= remainingLength) {
            cut = price[index] + memoHelper(index, remainingLength - rodPieceLen, price, dp);
        }

        return dp[index][remainingLength] = Math.max(notCut, cut);
    }

    // ==========================================
    // Approach 2: Bottom-Up DP (Tabulation)
    // ==========================================
    public static int cutRodTabulation(int[] price, int n) {
        int[][] dp = new int[n][n + 1];

        // Base case: using only pieces of length 1 (index 0)
        for (int len = 0; len <= n; len++) {
            dp[0][len] = len * price[0];
        }

        for (int i = 1; i < n; i++) {
            int rodPieceLen = i + 1;
            for (int len = 0; len <= n; len++) {
                int notCut = dp[i - 1][len];
                int cut = 0;
                if (rodPieceLen <= len) {
                    cut = price[i] + dp[i][len - rodPieceLen]; // Stay at index i
                }
                dp[i][len] = Math.max(notCut, cut);
            }
        }

        return dp[n - 1][n];
    }

    // ==========================================
    // Approach 3: Space-Optimized DP - O(N) Space
    // ==========================================
    public static int cutRodSpaceOptimized(int[] price, int n) {
        int[] dp = new int[n + 1];

        // Base case: length 1 pieces
        for (int len = 0; len <= n; len++) {
            dp[len] = len * price[0];
        }

        for (int i = 1; i < n; i++) {
            int rodPieceLen = i + 1;
            // Forward iteration compounds choices of rodPieceLen
            for (int len = rodPieceLen; len <= n; len++) {
                dp[len] = Math.max(dp[len], price[i] + dp[len - rodPieceLen]);
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        // Test Case 1:
        // price = [1, 5, 8, 9, 10, 17, 17, 20], n = 8
        // Cuts of lengths 2 and 6 -> price 5 + 17 = 22
        int[] price1 = {1, 5, 8, 9, 10, 17, 17, 20};
        int n1 = price1.length;
        System.out.println("Test 1 (price=[1,5,8,9,10,17,17,20], n=8):");
        System.out.println("  Memoization:     " + cutRodMemo(price1, n1)); // 22
        System.out.println("  Tabulation:      " + cutRodTabulation(price1, n1)); // 22
        System.out.println("  Space-Optimized: " + cutRodSpaceOptimized(price1, n1)); // 22

        // Test Case 2:
        // price = [3, 5, 8, 9, 10, 17, 17, 20], n = 8
        // Cuts of 8 pieces of length 1 -> 8 * 3 = 24
        int[] price2 = {3, 5, 8, 9, 10, 17, 17, 20};
        int n2 = price2.length;
        System.out.println("\nTest 2 (price=[3,5,8,9,10,17,17,20], n=8):");
        System.out.println("  Memoization:     " + cutRodMemo(price2, n2)); // 24
        System.out.println("  Tabulation:      " + cutRodTabulation(price2, n2)); // 24
        System.out.println("  Space-Optimized: " + cutRodSpaceOptimized(price2, n2)); // 24
    }
}
