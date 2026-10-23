/*
QUESTION:-
Given a triangle array, return the minimum path sum from top to bottom.

For each step, you may move to an adjacent number of the row below. More formally, if you are on index i on the current row, you may move to either index i or index i + 1 on the next row.

Example:
Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
Output: 11
Explanation: The triangle looks like:
   2
  3 4
 6 5 7
4 1 8 3
The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).

Approach:
1. We can solve this problem using dynamic programming with memoization (top-down approach).
2. We define a helper function fmemo(i, j, n, tri, dp) that calculates the minimum path sum from the current cell (i, j) to the bottom of the triangle.
3. The function checks if the minimum path sum for the current cell is already calculated and stored in the dp array. If yes, it returns the value from dp.
4. If i is equal to n-1, it means we are at the last row of the triangle, so the minimum path sum is the value in the current cell (tri[i][j]).
5. Otherwise, the function calculates the minimum path sum for the current cell by taking the minimum of the minimum path sums from the cells directly below (i+1, j) and (i+1, j+1), and then adding the value in the current cell (tri[i][j]).
6. The result is stored in the dp array to avoid recomputation when the function is called with the same parameters again.

Complexity Analysis:
- The time complexity of the fmemo function is O(n^2) because we are calculating the minimum path sum for each cell only once and storing it in the dp array.
- The space complexity is also O(n^2) to store the dp array.

*/

import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// CODE:-
class Triangle {

    // ==========================================
    // Approach 1: Top-Down DP (Memoization)
    // ==========================================
    public static int minimumTotalMemo(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MIN_VALUE);
        }
        return memoHelper(0, 0, triangle, dp, n);
    }

    private static int memoHelper(int i, int j, List<List<Integer>> triangle, int[][] dp, int n) {
        if (i == n - 1) {
            return triangle.get(i).get(j);
        }
        if (dp[i][j] != Integer.MIN_VALUE) {
            return dp[i][j];
        }

        int down = triangle.get(i).get(j) + memoHelper(i + 1, j, triangle, dp, n);
        int diagonal = triangle.get(i).get(j) + memoHelper(i + 1, j + 1, triangle, dp, n);

        return dp[i][j] = Math.min(down, diagonal);
    }

    // ==========================================
    // Approach 2: Bottom-Up DP (Tabulation)
    // ==========================================
    public static int minimumTotalTabulation(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n];

        // Base case: copy bottom row
        for (int j = 0; j < n; j++) {
            dp[n - 1][j] = triangle.get(n - 1).get(j);
        }

        // Fill upwards from row n-2 to row 0
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                int down = dp[i + 1][j];
                int diagonal = dp[i + 1][j + 1];
                dp[i][j] = triangle.get(i).get(j) + Math.min(down, diagonal);
            }
        }

        return dp[0][0];
    }

    // ==========================================
    // Approach 3: Space-Optimized DP - O(N) Space
    // ==========================================
    public static int minimumTotalSpaceOptimized(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n];

        // Initialize with bottom row
        for (int j = 0; j < n; j++) {
            dp[j] = triangle.get(n - 1).get(j);
        }

        // Iterate from second to last row up to row 0
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[j] = triangle.get(i).get(j) + Math.min(dp[j], dp[j + 1]);
            }
        }

        return dp[0];
    }

    public static void main(String[] args) {
        // Test Case 1:
        // triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
        // Minimum path: 2 + 3 + 5 + 1 = 11
        List<List<Integer>> triangle1 = new ArrayList<>();
        triangle1.add(Arrays.asList(2));
        triangle1.add(Arrays.asList(3, 4));
        triangle1.add(Arrays.asList(6, 5, 7));
        triangle1.add(Arrays.asList(4, 1, 8, 3));

        System.out.println("Test 1:");
        System.out.println("  Memoization:     " + minimumTotalMemo(triangle1)); // 11
        System.out.println("  Tabulation:      " + minimumTotalTabulation(triangle1)); // 11
        System.out.println("  Space-Optimized: " + minimumTotalSpaceOptimized(triangle1)); // 11

        // Test Case 2:
        // triangle = [[-10]] -> Expected: -10
        List<List<Integer>> triangle2 = new ArrayList<>();
        triangle2.add(Arrays.asList(-10));

        System.out.println("\nTest 2:");
        System.out.println("  Memoization:     " + minimumTotalMemo(triangle2)); // -10
        System.out.println("  Tabulation:      " + minimumTotalTabulation(triangle2)); // -10
        System.out.println("  Space-Optimized: " + minimumTotalSpaceOptimized(triangle2)); // -10
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
