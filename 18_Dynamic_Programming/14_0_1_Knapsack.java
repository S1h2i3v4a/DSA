/**
 * Problem Name: 0/1 Knapsack Problem
 * Platform: GeeksforGeeks / Standard Data Structures & Algorithms
 * Difficulty: Medium
 *
 * Time Complexity: O(N * W) where N is number of items and W is knapsack capacity
 * Space Complexity:
 *   - Memoization: O(N * W) table + O(N) recursion stack
 *   - Tabulation: O(N * W) table
 *   - Space-Optimized DP: O(W) 1D array
 *
 * Approach:
 *   - We have N items, each with a given weight wt[i] and value val[i].
 *   - A knapsack can carry a maximum capacity of W.
 *   - Each item can either be included once (1) or excluded (0).
 *   - Maximize the total value without exceeding capacity W.
 *   - Recurrence:
 *     f(i, w) = max(val[i] + f(i - 1, w - wt[i]), f(i - 1, w))
 *   - Base Case:
 *     At index 0: if wt[0] <= w, return val[0]; else return 0.
 *   - Space Optimization:
 *     By traversing capacity `w` backwards from W down to wt[i], a single 1D array of size W + 1
 *     preserves the un-overwritten results from the previous item i - 1.
 */

import java.util.Arrays;

class ZeroOneKnapsack {

    // ==========================================
    // Approach 1: Top-Down DP (Memoization)
    // ==========================================
    public static int knapSackMemo(int W, int[] wt, int[] val, int n) {
        int[][] dp = new int[n][W + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return memoHelper(n - 1, W, wt, val, dp);
    }

    private static int memoHelper(int index, int w, int[] wt, int[] val, int[][] dp) {
        if (index == 0) {
            return (wt[0] <= w) ? val[0] : 0;
        }

        if (dp[index][w] != -1) {
            return dp[index][w];
        }

        int notPick = memoHelper(index - 1, w, wt, val, dp);
        int pick = (int) -1e9;
        if (wt[index] <= w) {
            pick = val[index] + memoHelper(index - 1, w - wt[index], wt, val, dp);
        }

        return dp[index][w] = Math.max(notPick, pick);
    }

    // ==========================================
    // Approach 2: Bottom-Up DP (Tabulation)
    // ==========================================
    public static int knapSackTabulation(int W, int[] wt, int[] val, int n) {
        int[][] dp = new int[n][W + 1];

        // Base case for index 0
        for (int w = wt[0]; w <= W; w++) {
            dp[0][w] = val[0];
        }

        for (int i = 1; i < n; i++) {
            for (int w = 0; w <= W; w++) {
                int notPick = dp[i - 1][w];
                int pick = (int) -1e9;
                if (wt[i] <= w) {
                    pick = val[i] + dp[i - 1][w - wt[i]];
                }
                dp[i][w] = Math.max(notPick, pick);
            }
        }

        return dp[n - 1][W];
    }

    // ==========================================
    // Approach 3: Space-Optimized DP - O(W) Space
    // ==========================================
    public static int knapSackSpaceOptimized(int W, int[] wt, int[] val, int n) {
        int[] dp = new int[W + 1];

        // Base case: initialize for item 0
        for (int w = wt[0]; w <= W; w++) {
            dp[w] = val[0];
        }

        for (int i = 1; i < n; i++) {
            // Traverse backwards so previous row values are not prematurely overwritten
            for (int w = W; w >= wt[i]; w--) {
                dp[w] = Math.max(dp[w], val[i] + dp[w - wt[i]]);
            }
        }

        return dp[W];
    }

    public static void main(String[] args) {
        // Test Case 1:
        // W = 4, wt = [4, 5, 1], val = [1, 2, 3] -> Expected: 3 (Item 2 with weight 1 and val 3)
        int W1 = 4;
        int[] wt1 = {4, 5, 1};
        int[] val1 = {1, 2, 3};
        int n1 = wt1.length;
        System.out.println("Test 1:");
        System.out.println("  Memoization:     " + knapSackMemo(W1, wt1, val1, n1)); // 3
        System.out.println("  Tabulation:      " + knapSackTabulation(W1, wt1, val1, n1)); // 3
        System.out.println("  Space-Optimized: " + knapSackSpaceOptimized(W1, wt1, val1, n1)); // 3

        // Test Case 2:
        // W = 50, wt = [10, 20, 30], val = [60, 100, 120] -> Expected: 220 (Items 1 and 2: wt 20+30=50, val 100+120=220)
        int W2 = 50;
        int[] wt2 = {10, 20, 30};
        int[] val2 = {60, 100, 120};
        int n2 = wt2.length;
        System.out.println("\nTest 2:");
        System.out.println("  Memoization:     " + knapSackMemo(W2, wt2, val2, n2)); // 220
        System.out.println("  Tabulation:      " + knapSackTabulation(W2, wt2, val2, n2)); // 220
        System.out.println("  Space-Optimized: " + knapSackSpaceOptimized(W2, wt2, val2, n2)); // 220
    }
}
