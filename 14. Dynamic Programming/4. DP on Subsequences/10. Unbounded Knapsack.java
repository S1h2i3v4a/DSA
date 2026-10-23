/*
QUESTION:
Given a set of N items, each with a weight and a value, represented 
by the arrays w[] and val[] respectively, and a knapsack with weight 
limit 'W', the task is to fill the knapsack in such a way that we 
can get the maximum profit. Return the maximum profit.
Note: Each item can be taken any number of times.

Example:
Input: N = 2, W = 3
val[] = {1, 1}
wt[] = {2, 1}
Output: 3
Explanation: 
1. Pick the 2nd element thrice.
2. Total profit = 1 + 1 + 1 = 3. Also the total weight = 1 + 1 + 1 = 3 
   which is <= W.

APPROACH:
- This problem can be solved using Dynamic Programming (DP).
- Initialize a 2D DP array 'dp' where dp[i][j] represents the maximum 
  profit that can be obtained by using items from 0 to 'i' with a total 
  weight of 'j'.
- Base case: dp[0][j] = j / wt[0] * val[0] (maximum profit with only 
  the first item)
- For each item 'i' and each possible total weight 'j':
  - dp[i][j] = max(dp[i-1][j], val[i] + dp[i][j - wt[i]])
- The required answer will be dp[N-1][W] where 'N' is the number of items 
  and 'W' is the given weight limit.

COMPLEXITY ANALYSIS:
- The time complexity of the solution is O(N * W), where 'N' is the number 
  of items and 'W' is the given weight limit.
- The space complexity is O(N * W) for the dp array.

CODE:
*/

import java.io.*;
import java.util.*;
import java.util.Arrays;

// CODE:-
class UnboundedKnapsack {

    // ==========================================
    // Approach 1: Top-Down DP (Memoization)
    // ==========================================
    public static int knapSackMemo(int N, int W, int[] val, int[] wt) {
        int[][] dp = new int[N][W + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return memoHelper(N - 1, W, val, wt, dp);
    }

    private static int memoHelper(int index, int cap, int[] val, int[] wt, int[][] dp) {
        if (index == 0) {
            return (cap / wt[0]) * val[0];
        }

        if (dp[index][cap] != -1) {
            return dp[index][cap];
        }

        int notPick = memoHelper(index - 1, cap, val, wt, dp);
        int pick = 0;
        if (wt[index] <= cap) {
            pick = val[index] + memoHelper(index, cap - wt[index], val, wt, dp);
        }

        return dp[index][cap] = Math.max(notPick, pick);
    }

    // ==========================================
    // Approach 2: Bottom-Up DP (Tabulation)
    // ==========================================
    public static int knapSackTabulation(int N, int W, int[] val, int[] wt) {
        int[][] dp = new int[N][W + 1];

        // Base case for index 0
        for (int w = 0; w <= W; w++) {
            dp[0][w] = (w / wt[0]) * val[0];
        }

        for (int i = 1; i < N; i++) {
            for (int w = 0; w <= W; w++) {
                int notPick = dp[i - 1][w];
                int pick = 0;
                if (wt[i] <= w) {
                    pick = val[i] + dp[i][w - wt[i]]; // Stay at row i
                }
                dp[i][w] = Math.max(notPick, pick);
            }
        }

        return dp[N - 1][W];
    }

    // ==========================================
    // Approach 3: Space-Optimized DP - O(W) Space
    // ==========================================
    public static int knapSackSpaceOptimized(int N, int W, int[] val, int[] wt) {
        int[] dp = new int[W + 1];

        // Base case: initialize with item 0
        for (int w = 0; w <= W; w++) {
            dp[w] = (w / wt[0]) * val[0];
        }

        for (int i = 1; i < N; i++) {
            // Forward traversal allows compounding the same item multiple times
            for (int w = wt[i]; w <= W; w++) {
                dp[w] = Math.max(dp[w], val[i] + dp[w - wt[i]]);
            }
        }

        return dp[W];
    }

    public static void main(String[] args) {
        // Test Case 1:
        // N = 2, W = 3, val = [1, 1], wt = [2, 1] -> Expected: 3 (Pick item 1 three times: 3 * 1 = 3)
        int N1 = 2;
        int W1 = 3;
        int[] val1 = {1, 1};
        int[] wt1 = {2, 1};
        System.out.println("Test 1:");
        System.out.println("  Memoization:     " + knapSackMemo(N1, W1, val1, wt1)); // 3
        System.out.println("  Tabulation:      " + knapSackTabulation(N1, W1, val1, wt1)); // 3
        System.out.println("  Space-Optimized: " + knapSackSpaceOptimized(N1, W1, val1, wt1)); // 3

        // Test Case 2:
        // N = 4, W = 8, val = [1, 4, 5, 7], wt = [1, 3, 4, 5] -> Expected: 11
        // (Item with wt 3 and val 4 taken 2 times + item with wt 2 -> wt 3*2 + 1*2 = 8, val 4*2 + 1*2 = 10;
        //  Or item with wt 5 and val 7 + item wt 3 val 4 = wt 8, val 11!)
        int N2 = 4;
        int W2 = 8;
        int[] val2 = {1, 4, 5, 7};
        int[] wt2 = {1, 3, 4, 5};
        System.out.println("\nTest 2:");
        System.out.println("  Memoization:     " + knapSackMemo(N2, W2, val2, wt2)); // 11
        System.out.println("  Tabulation:      " + knapSackTabulation(N2, W2, val2, wt2)); // 11
        System.out.println("  Space-Optimized: " + knapSackSpaceOptimized(N2, W2, val2, wt2)); // 11
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
