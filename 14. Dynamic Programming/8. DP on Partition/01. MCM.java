/*
QUESTION:
Given a sequence of matrices, find the most efficient way to multiply these matrices together. The efficient way is the one that involves the least number of multiplications.
The dimensions of the matrices are given in an array arr[] of size N (such that N = number of matrices + 1) where the ith matrix has the dimensions (arr[i-1] x arr[i]).

Example 1:
Input: N = 5
arr = {40, 20, 30, 10, 30}
Output: 26000
Explanation: There are 4 matrices of dimension 
40x20, 20x30, 30x10, 10x30. Say the matrices are 
named as A, B, C, D. Out of all possible combinations,
the most efficient way is (A*(B*C))*D. 
The number of operations are -
20*30*10 + 40*20*10 + 40*10*30 = 26000.

APPROACH:
- This problem can be solved using dynamic programming.
- The dp[i][j] represents the minimum number of scalar multiplications required to compute the matrix chain multiplication of matrices from i to j.
- Iterate through the chain lengths from 2 to N (chain length of 2 means considering two matrices at a time).
- For each chain length, iterate through all possible starting matrices (i).
- For each starting matrix, calculate the cost of multiplying the matrices and update dp[i][j].
- Finally, return dp[1][N-1], which represents the minimum number of scalar multiplications needed for the entire matrix chain multiplication.

COMPLEXITY ANALYSIS:
- The time complexity of this approach is O(n^3), where n is the length of the array arr.
- The space complexity is O(n^2) due to the dp array.
- Overall, the algorithm runs in O(n^3) time and O(n^2) space.

CODE:-
*/

import java.io.*;
import java.util.*;
import java.util.Arrays;

// CODE:-
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
