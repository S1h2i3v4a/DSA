/**
 * Problem Name: Cherry Pickup II (Chocolates Pickup - 3D DP)
 * Platform: LeetCode (LC 1463) / GeeksforGeeks
 * Difficulty: Hard
 *
 * Time Complexity: O(R * C^2 * 9) = O(R * C^2) where R is rows, C is columns
 * Space Complexity:
 *   - Memoization: O(R * C^2) table + O(R) recursion stack
 *   - Tabulation: O(R * C^2) table
 *   - Space-Optimized DP: O(C^2) using two 2D arrays (prev and curr)
 *
 * Approach:
 *   - Two robots start simultaneously at top-left (0, 0) and top-right (0, C-1).
 *   - Both robots advance one row down each step to (r+1, c+dc) where dc in {-1, 0, 1}.
 *   - Since both robots always move to row `r + 1` together, a single row index `r` suffices
 *     to represent their shared depth, reducing a 4D state to 3D: (r, c1, c2).
 *   - Cherries collected at step (r, c1, c2):
 *     If c1 == c2: grid[r][c1] (only one robot picks if in the same cell)
 *     Else: grid[r][c1] + grid[r][c2]
 *   - Recurrence:
 *     dp(r, c1, c2) = value + max_{dc1, dc2 in {-1, 0, 1}} dp(r + 1, c1 + dc1, c2 + dc2)
 *   - We provide:
 *     1. Top-Down 3D DP (Memoization)
 *     2. Bottom-Up 3D DP (Tabulation)
 *     3. Space-Optimized 3D DP using O(C^2) space.
 */

import java.util.Arrays;

class CherryPickupII {

    // ==========================================
    // Approach 1: Top-Down 3D DP (Memoization)
    // ==========================================
    public static int cherryPickupMemo(int[][] grid) {
        int r = grid.length;
        int c = grid[0].length;
        int[][][] dp = new int[r][c][c];
        for (int[][] mat : dp) {
            for (int[] row : mat) {
                Arrays.fill(row, -1);
            }
        }
        return memoHelper(0, 0, c - 1, grid, dp, r, c);
    }

    private static int memoHelper(int i, int j1, int j2, int[][] grid, int[][][] dp, int r, int c) {
        // Boundary condition
        if (j1 < 0 || j1 >= c || j2 < 0 || j2 >= c) {
            return (int) -1e9;
        }

        // Base case: destination reached at bottom row
        if (i == r - 1) {
            if (j1 == j2) return grid[i][j1];
            else return grid[i][j1] + grid[i][j2];
        }

        if (dp[i][j1][j2] != -1) {
            return dp[i][j1][j2];
        }

        int maxCherries = (int) -1e9;
        // Explore all 9 combinations of moves for both robots
        for (int dj1 = -1; dj1 <= 1; dj1++) {
            for (int dj2 = -1; dj2 <= 1; dj2++) {
                int value = (j1 == j2) ? grid[i][j1] : grid[i][j1] + grid[i][j2];
                value += memoHelper(i + 1, j1 + dj1, j2 + dj2, grid, dp, r, c);
                maxCherries = Math.max(maxCherries, value);
            }
        }

        return dp[i][j1][j2] = maxCherries;
    }

    // ==========================================
    // Approach 2: Bottom-Up 3D DP (Tabulation)
    // ==========================================
    public static int cherryPickupTabulation(int[][] grid) {
        int r = grid.length;
        int c = grid[0].length;
        int[][][] dp = new int[r][c][c];

        // Base case: bottom row
        for (int j1 = 0; j1 < c; j1++) {
            for (int j2 = 0; j2 < c; j2++) {
                if (j1 == j2) dp[r - 1][j1][j2] = grid[r - 1][j1];
                else dp[r - 1][j1][j2] = grid[r - 1][j1] + grid[r - 1][j2];
            }
        }

        // Fill upwards from row r - 2 to row 0
        for (int i = r - 2; i >= 0; i--) {
            for (int j1 = 0; j1 < c; j1++) {
                for (int j2 = 0; j2 < c; j2++) {
                    int maxCherries = (int) -1e9;
                    for (int dj1 = -1; dj1 <= 1; dj1++) {
                        for (int dj2 = -1; dj2 <= 1; dj2++) {
                            int nj1 = j1 + dj1;
                            int nj2 = j2 + dj2;
                            int value = (j1 == j2) ? grid[i][j1] : grid[i][j1] + grid[i][j2];
                            if (nj1 >= 0 && nj1 < c && nj2 >= 0 && nj2 < c) {
                                value += dp[i + 1][nj1][nj2];
                            } else {
                                value += (int) -1e9;
                            }
                            maxCherries = Math.max(maxCherries, value);
                        }
                    }
                    dp[i][j1][j2] = maxCherries;
                }
            }
        }

        return dp[0][0][c - 1];
    }

    // ==========================================
    // Approach 3: Space-Optimized DP - O(C^2) Space
    // ==========================================
    public static int cherryPickupSpaceOptimized(int[][] grid) {
        int r = grid.length;
        int c = grid[0].length;
        int[][] prev = new int[c][c];

        // Base case: bottom row
        for (int j1 = 0; j1 < c; j1++) {
            for (int j2 = 0; j2 < c; j2++) {
                if (j1 == j2) prev[j1][j2] = grid[r - 1][j1];
                else prev[j1][j2] = grid[r - 1][j1] + grid[r - 1][j2];
            }
        }

        for (int i = r - 2; i >= 0; i--) {
            int[][] curr = new int[c][c];
            for (int j1 = 0; j1 < c; j1++) {
                for (int j2 = 0; j2 < c; j2++) {
                    int maxCherries = (int) -1e9;
                    for (int dj1 = -1; dj1 <= 1; dj1++) {
                        for (int dj2 = -1; dj2 <= 1; dj2++) {
                            int nj1 = j1 + dj1;
                            int nj2 = j2 + dj2;
                            int value = (j1 == j2) ? grid[i][j1] : grid[i][j1] + grid[i][j2];
                            if (nj1 >= 0 && nj1 < c && nj2 >= 0 && nj2 < c) {
                                value += prev[nj1][nj2];
                            } else {
                                value += (int) -1e9;
                            }
                            maxCherries = Math.max(maxCherries, value);
                        }
                    }
                    curr[j1][j2] = maxCherries;
                }
            }
            prev = curr;
        }

        return prev[0][c - 1];
    }

    public static void main(String[] args) {
        // Test Case 1:
        // grid = [[3,1,1],[2,5,1],[1,5,5],[2,1,1]]
        // Expected: 24
        int[][] grid1 = {
            {3, 1, 1},
            {2, 5, 1},
            {1, 5, 5},
            {2, 1, 1}
        };
        System.out.println("Test 1:");
        System.out.println("  Memoization:     " + cherryPickupMemo(grid1)); // 24
        System.out.println("  Tabulation:      " + cherryPickupTabulation(grid1)); // 24
        System.out.println("  Space-Optimized: " + cherryPickupSpaceOptimized(grid1)); // 24

        // Test Case 2:
        // grid = [[1,0,0,0,0,0,1],[2,0,0,0,0,3,0],[2,0,9,0,0,0,0],[0,3,0,5,4,0,0],[1,0,2,3,0,0,6]]
        // Expected: 28
        int[][] grid2 = {
            {1, 0, 0, 0, 0, 0, 1},
            {2, 0, 0, 0, 0, 3, 0},
            {2, 0, 9, 0, 0, 0, 0},
            {0, 3, 0, 5, 4, 0, 0},
            {1, 0, 2, 3, 0, 0, 6}
        };
        System.out.println("\nTest 2:");
        System.out.println("  Memoization:     " + cherryPickupMemo(grid2)); // 28
        System.out.println("  Tabulation:      " + cherryPickupTabulation(grid2)); // 28
        System.out.println("  Space-Optimized: " + cherryPickupSpaceOptimized(grid2)); // 28
    }
}
