/**
 * Problem Name: Unique Paths II (Grid with Obstacles)
 * Platform: LeetCode (LC 63) / GeeksforGeeks
 * Difficulty: Medium
 *
 * Time Complexity: O(M * N) where M is rows and N is columns
 * Space Complexity:
 *   - Memoization: O(M * N) table + O(M + N) recursion stack
 *   - Tabulation: O(M * N) table
 *   - Space-Optimized DP: O(N) rolling 1D array
 *
 * Approach:
 *   - Similar to Unique Paths, but certain cells contain obstacles ('1').
 *   - If obstacleGrid[0][0] == 1 or obstacleGrid[M-1][N-1] == 1, there is no feasible path (return 0).
 *   - Recurrence:
 *     If obstacleGrid[r][c] == 1:
 *       paths(r, c) = 0
 *     Else:
 *       paths(r, c) = paths(r - 1, c) + paths(r, c - 1)
 *   - We provide:
 *     1. Top-Down DP with Memoization
 *     2. Bottom-Up DP with Tabulation
 *     3. Space-Optimized DP using an O(N) 1D array.
 */

import java.util.Arrays;

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
