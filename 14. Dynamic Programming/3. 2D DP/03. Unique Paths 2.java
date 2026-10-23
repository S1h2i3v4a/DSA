/*
QUESTION:-
You are given an m x n integer array grid. There is a robot initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.

An obstacle and space are marked as 1 or 0 respectively in grid. A path that the robot takes cannot include any square that is an obstacle.

Return the number of possible unique paths that the robot can take to reach the bottom-right corner.

Example:
Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
Output: 2
Explanation: There is one obstacle in the middle of the 3x3 grid above.
There are two ways to reach the bottom-right corner:
1. Right -> Right -> Down -> Down
2. Down -> Down -> Right -> Right

Approach:
1. We can solve this problem using dynamic programming with memoization (top-down approach).
2. We define a helper function fmemo(i, j, grid, dp) that calculates the number of unique paths from the top-left corner (0, 0) to the cell (i, j) on the grid.
3. The function checks if the number of unique paths for the cell (i, j) is already calculated and stored in the dp array. If yes, it returns the value from dp.
4. If the cell (i, j) contains an obstacle (i.e., grid[i][j] == 1), then there is no possible path through that cell, so the function returns 0.
5. Otherwise, it calculates the number of unique paths for the cell (i, j) by adding the number of unique paths from the cell above (i-1, j) and the cell to the left (i, j-1).
6. The base case is when i or j is less than 0, in which case the function returns 0 as there are no paths to reach those cells.
7. In the main function uniquePathsWithObstacles(obstacleGrid), we create a dp array of size m x n initialized with -1 and call the fmemo function for the bottom-right cell (m-1, n-1).
8. Finally, we return the number of unique paths obtained for the bottom-right cell as the result.

Complexity Analysis:
- The time complexity of the fmemo function is O(m x n) because we are calculating the number of unique paths for each cell only once and storing it in the dp array.
- The space complexity is also O(m x n) to store the dp array.

Note: The given test cases are generated such that the answer will be less than or equal to 2 x 10^9.
*/

import java.io.*;
import java.util.*;
import java.util.Arrays;

// CODE:-
class UniquePathsII {

    // ==========================================
    // Approach 1: Top-Down DP (Memoization)
    // ==========================================
    public static int uniquePathsWithObstaclesMemo(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid[0][0] == 1) return 0;
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (obstacleGrid[m - 1][n - 1] == 1) return 0;

        int[][] dp = new int[m][n];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return memoHelper(m - 1, n - 1, obstacleGrid, dp);
    }

    private static int memoHelper(int r, int c, int[][] grid, int[][] dp) {
        if (r < 0 || c < 0 || grid[r][c] == 1) return 0;
        if (r == 0 && c == 0) return 1;
        if (dp[r][c] != -1) return dp[r][c];

        int up = memoHelper(r - 1, c, grid, dp);
        int left = memoHelper(r, c - 1, grid, dp);

        return dp[r][c] = up + left;
    }

    // ==========================================
    // Approach 2: Bottom-Up DP (Tabulation)
    // ==========================================
    public static int uniquePathsWithObstaclesTabulation(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid[0][0] == 1) return 0;
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else if (i == 0 && j == 0) {
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
    public static int uniquePathsWithObstaclesSpaceOptimized(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid[0][0] == 1) return 0;
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int[] dp = new int[n];
        dp[0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0; // Obstacle blocks all paths through this column
                } else if (j > 0) {
                    dp[j] += dp[j - 1]; // dp[j] holds previous row value (up), dp[j-1] holds current row left
                }
            }
        }

        return dp[n - 1];
    }

    public static void main(String[] args) {
        // Test Case 1:
        // grid = [[0,0,0],[0,1,0],[0,0,0]] -> Expected: 2
        int[][] grid1 = {
            {0, 0, 0},
            {0, 1, 0},
            {0, 0, 0}
        };
        System.out.println("Test 1 (Obstacle in center):");
        System.out.println("  Memoization:     " + uniquePathsWithObstaclesMemo(grid1));
        System.out.println("  Tabulation:      " + uniquePathsWithObstaclesTabulation(grid1));
        System.out.println("  Space-Optimized: " + uniquePathsWithObstaclesSpaceOptimized(grid1));

        // Test Case 2:
        // grid = [[0,1],[0,0]] -> Expected: 1
        int[][] grid2 = {
            {0, 1},
            {0, 0}
        };
        System.out.println("\nTest 2 (Obstacle on path):");
        System.out.println("  Memoization:     " + uniquePathsWithObstaclesMemo(grid2));
        System.out.println("  Tabulation:      " + uniquePathsWithObstaclesTabulation(grid2));
        System.out.println("  Space-Optimized: " + uniquePathsWithObstaclesSpaceOptimized(grid2));
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
