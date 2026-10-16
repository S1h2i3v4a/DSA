/**
 * Problem Name: Triangle (Minimum Path Sum in Triangular Grid)
 * Platform: LeetCode (LC 120) / GeeksforGeeks
 * Difficulty: Medium
 *
 * Time Complexity: O(N^2) where N is the number of rows in the triangle
 * Space Complexity:
 *   - Memoization: O(N^2) table + O(N) recursion stack
 *   - Tabulation: O(N^2) table
 *   - Space-Optimized DP: O(N) 1D array
 *
 * Approach:
 *   - Given a triangle where row `i` has `i + 1` elements.
 *   - From cell (i, j), we can only move to (i + 1, j) or (i + 1, j + 1).
 *   - Bottom-Up Tabulation is uniquely elegant for this problem:
 *     Instead of tracking multiple possible endpoints on the bottom row, we start from the bottom
 *     and move UPWARD to the apex (0, 0).
 *     Base Case: Bottom row values remain as they are.
 *     Recurrence:
 *       dp[i][j] = triangle[i][j] + min(dp[i + 1][j], dp[i + 1][j + 1])
 *     Apex dp[0][0] naturally stores the global minimum path sum.
 *   - Space-Optimized DP:
 *     We can maintain a single 1D array `dp` of size N initialized with the bottom row values.
 *     For each row i from N-2 down to 0, update:
 *       dp[j] = triangle[i][j] + min(dp[j], dp[j + 1])
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
