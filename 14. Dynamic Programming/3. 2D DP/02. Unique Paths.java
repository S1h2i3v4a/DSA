/*
QUESTION:-
There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.

Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.

Example 1:
Input: m = 3, n = 7
Output: 28

Approach:
1. We can solve this problem using dynamic programming with memoization (top-down approach).
2. We define a helper function fmemo(i, j, dp) that calculates the number of unique paths from the top-left corner (0, 0) to the cell (i, j) on the grid.
3. The function checks if the number of unique paths for the cell (i, j) is already calculated and stored in the dp array. If yes, it returns the value from dp.
4. Otherwise, it calculates the number of unique paths for the cell (i, j) by adding the number of unique paths from the cell above (i-1, j) and the cell to the left (i, j-1).
5. The base case is when i or j is less than 0, in which case the function returns 0 as there are no paths to reach those cells.
6. In the main function uniquePaths(m, n), we create a dp array of size m x n initialized with -1 and call the fmemo function for the bottom-right cell (m-1, n-1).
7. Finally, we return the number of unique paths obtained for the bottom-right cell as the result.

Complexity Analysis:
- The time complexity of the fmemo function is O(m x n) because we are calculating the number of unique paths for each cell only once and storing it in the dp array.
- The space complexity is also O(m x n) to store the dp array.

Note: The given test cases are generated such that the answer will be less than or equal to 2 x 10^9.
*/

import java.io.*;
import java.util.*;
import java.util.Arrays;

// CODE:-
class UniquePaths {

    // ==========================================
    // Approach 1: Top-Down DP (Memoization)
    // ==========================================
    public static int uniquePathsMemo(int m, int n) {
        int[][] dp = new int[m][n];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return memoHelper(m - 1, n - 1, dp);
    }

    private static int memoHelper(int r, int c, int[][] dp) {
        if (r == 0 && c == 0) return 1;
        if (r < 0 || c < 0) return 0;
        if (dp[r][c] != -1) return dp[r][c];

        int up = memoHelper(r - 1, c, dp);
        int left = memoHelper(r, c - 1, dp);

        return dp[r][c] = up + left;
    }

    // ==========================================
    // Approach 2: Bottom-Up DP (Tabulation)
    // ==========================================
    public static int uniquePathsTabulation(int m, int n) {
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                } else {
                    int up = (i > 0) ? dp[i - 1][j] : 0;
                    int left = (j > 0) ? dp[i][j - 1] : 0;
                    dp[i][j] = up + left;
                }
            }
        }

        return dp[m - 1][n - 1];
    }

    // ==========================================
    // Approach 3: Space-Optimized DP - O(N) Space
    // ==========================================
    public static int uniquePathsSpaceOptimized(int m, int n) {
        int[] prev = new int[n];
        Arrays.fill(prev, 1); // Row 0 has only 1 way for all columns

        for (int i = 1; i < m; i++) {
            int[] curr = new int[n];
            curr[0] = 1; // Column 0 has only 1 way
            for (int j = 1; j < n; j++) {
                curr[j] = prev[j] + curr[j - 1];
            }
            prev = curr;
        }

        return prev[n - 1];
    }

    // ==========================================
    // Approach 4: Combinatorics Formula - O(min(M, N)) Time, O(1) Space
    // ==========================================
    public static int uniquePathsCombinatorics(int m, int n) {
        int totalSteps = m + n - 2;
        int r = Math.min(m - 1, n - 1);
        double result = 1.0;

        for (int i = 1; i <= r; i++) {
            result = result * (totalSteps - r + i) / i;
        }

        return (int) Math.round(result);
    }

    public static void main(String[] args) {
        // Test Case 1: m = 3, n = 7 -> Expected: 28
        System.out.println("Test 1 (m=3, n=7):");
        System.out.println("  Memoization:     " + uniquePathsMemo(3, 7));
        System.out.println("  Tabulation:      " + uniquePathsTabulation(3, 7));
        System.out.println("  Space-Optimized: " + uniquePathsSpaceOptimized(3, 7));
        System.out.println("  Combinatorics:   " + uniquePathsCombinatorics(3, 7));

        // Test Case 2: m = 3, n = 2 -> Expected: 3
        System.out.println("\nTest 2 (m=3, n=2):");
        System.out.println("  Memoization:     " + uniquePathsMemo(3, 2));
        System.out.println("  Tabulation:      " + uniquePathsTabulation(3, 2));
        System.out.println("  Space-Optimized: " + uniquePathsSpaceOptimized(3, 2));
        System.out.println("  Combinatorics:   " + uniquePathsCombinatorics(3, 2));
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
