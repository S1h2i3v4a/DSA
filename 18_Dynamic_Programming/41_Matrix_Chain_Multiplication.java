/**
 * Problem Name: Matrix Chain Multiplication (MCM)
 * Platform: GeeksforGeeks / Standard Data Structures & Algorithms
 * Difficulty: Hard
 *
 * Time Complexity: O(N^3) where N is array length
 * Space Complexity:
 *   - Memoization: O(N^2) table + O(N) recursion stack
 *   - Tabulation: O(N^2) table
 *
 * Approach:
 *   - Matrix Ai has dimensions arr[i-1] x arr[i].
 *   - Find the most efficient way to multiply these matrices together by placing parentheses.
 *   - Partition DP Pattern:
 *     For range [i, j], try every partition point k from i to j-1:
 *     Cost = f(i, k) + f(k + 1, j) + arr[i - 1] * arr[k] * arr[j].
 *     Base Case: When i == j (single matrix, 0 multiplications).
 *   - We provide:
 *     1. Top-Down DP with Memoization
 *     2. Bottom-Up DP with Tabulation
 */

import java.util.Arrays;

class MatrixChainMultiplication {

    // ==========================================
    // Approach 1: Top-Down DP (Memoization)
    // ==========================================
    public static int matrixMultiplicationMemo(int[] arr, int n) {
        int[][] dp = new int[n][n];
        for (int[] row : dp) Arrays.fill(row, -1);
        return memoHelper(1, n - 1, arr, dp);
    }

    private static int memoHelper(int i, int j, int[] arr, int[][] dp) {
        if (i == j) return 0;
        if (dp[i][j] != -1) return dp[i][j];

        int minCost = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            int cost = memoHelper(i, k, arr, dp)
                     + memoHelper(k + 1, j, arr, dp)
                     + arr[i - 1] * arr[k] * arr[j];
            minCost = Math.min(minCost, cost);
        }

        return dp[i][j] = minCost;
    }

    // ==========================================
    // Approach 2: Bottom-Up DP (Tabulation)
    // ==========================================
    public static int matrixMultiplicationTabulation(int[] arr, int n) {
        int[][] dp = new int[n][n];

        // Base case: i == j is naturally 0

        // Length of chain L from 2 to n - 1
        for (int i = n - 1; i >= 1; i--) {
            for (int j = i + 1; j < n; j++) {
                int minCost = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int cost = dp[i][k] + dp[k + 1][j] + arr[i - 1] * arr[k] * arr[j];
                    minCost = Math.min(minCost, cost);
                }
                dp[i][j] = minCost;
            }
        }

        return dp[1][n - 1];
    }

    public static void main(String[] args) {
        // Test Case 1: arr = [40, 20, 30, 10, 30] -> Expected: 26000
        int[] arr1 = {40, 20, 30, 10, 30};
        int n1 = arr1.length;
        System.out.println("Test 1: " + matrixMultiplicationTabulation(arr1, n1)); // 26000

        // Test Case 2: arr = [10, 20, 30, 40, 30] -> Expected: 30000
        int[] arr2 = {10, 20, 30, 40, 30};
        int n2 = arr2.length;
        System.out.println("Test 2: " + matrixMultiplicationTabulation(arr2, n2)); // 30000
    }
}
