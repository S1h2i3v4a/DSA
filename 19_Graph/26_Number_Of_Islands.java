/**
 * Problem Name: Number of Islands
 * Platform: LeetCode (LC 200) / GeeksforGeeks
 * Difficulty: Medium
 *
 * Time Complexity: O(M * N) where M is rows and N is columns
 * Space Complexity: O(M * N) auxiliary recursion stack / queue in worst case
 *
 * Approach:
 *   - The problem asks for the count of distinct connected land components ('1's).
 *   - We scan every cell (r, c) in the grid:
 *     - If the cell contains '1', we increment our island count.
 *     - We then trigger a flood fill (DFS or BFS) starting at (r, c) to visit
 *       and sink/mark all connected land cells (e.g., turning '1' to '0' or marking visited).
 *   - In LeetCode 200, an island connects 4-directionally (up, down, left, right).
 *   - In GeeksforGeeks, an island often connects 8-directionally (including diagonals).
 *   - We provide:
 *     1. 4-Directional DFS (LeetCode standard)
 *     2. 4-Directional BFS (Iterative Queue)
 *     3. 8-Directional DFS (GFG standard)
 */

import java.util.LinkedList;
import java.util.Queue;

class NumberOfIslands {

    // ==========================================
    // Approach 1: 4-Directional DFS (LeetCode 200)
    // ==========================================
    public static int numIslandsDFS(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int m = grid.length;
        int n = grid[0].length;
        int count = 0;

        // Clone grid if in-place modification is not preferred,
        // or mutate directly if permissible
        char[][] copy = new char[m][n];
        for (int i = 0; i < m; i++) {
            System.arraycopy(grid[i], 0, copy[i], 0, n);
        }

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (copy[r][c] == '1') {
                    count++;
                    dfsSink(copy, r, c, m, n);
                }
            }
        }
        return count;
    }

    private static void dfsSink(char[][] grid, int r, int c, int m, int n) {
        if (r < 0 || r >= m || c < 0 || c >= n || grid[r][c] != '1') {
            return;
        }

        // Sink current land cell
        grid[r][c] = '0';

        // 4 directions: Up, Down, Left, Right
        dfsSink(grid, r - 1, c, m, n);
        dfsSink(grid, r + 1, c, m, n);
        dfsSink(grid, r, c - 1, m, n);
        dfsSink(grid, r, c + 1, m, n);
    }

    // ==========================================
    // Approach 2: 4-Directional BFS
    // ==========================================
    public static int numIslandsBFS(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int m = grid.length;
        int n = grid[0].length;
        int count = 0;

        char[][] copy = new char[m][n];
        for (int i = 0; i < m; i++) {
            System.arraycopy(grid[i], 0, copy[i], 0, n);
        }

        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (copy[r][c] == '1') {
                    count++;
                    copy[r][c] = '0';
                    Queue<int[]> queue = new LinkedList<>();
                    queue.offer(new int[]{r, c});

                    while (!queue.isEmpty()) {
                        int[] curr = queue.poll();
                        for (int[] d : dirs) {
                            int nr = curr[0] + d[0];
                            int nc = curr[1] + d[1];
                            if (nr >= 0 && nr < m && nc >= 0 && nc < n && copy[nr][nc] == '1') {
                                copy[nr][nc] = '0'; // Mark visited upon queuing
                                queue.offer(new int[]{nr, nc});
                            }
                        }
                    }
                }
            }
        }
        return count;
    }

    // ==========================================
    // Approach 3: 8-Directional DFS (GFG Variant)
    // ==========================================
    public static int numIslands8Dir(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int m = grid.length;
        int n = grid[0].length;
        int count = 0;

        char[][] copy = new char[m][n];
        for (int i = 0; i < m; i++) {
            System.arraycopy(grid[i], 0, copy[i], 0, n);
        }

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (copy[r][c] == '1') {
                    count++;
                    dfs8Dir(copy, r, c, m, n);
                }
            }
        }
        return count;
    }

    private static void dfs8Dir(char[][] grid, int r, int c, int m, int n) {
        if (r < 0 || r >= m || c < 0 || c >= n || grid[r][c] != '1') {
            return;
        }

        grid[r][c] = '0';

        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (dr != 0 || dc != 0) {
                    dfs8Dir(grid, r + dr, c + dc, m, n);
                }
            }
        }
    }

    public static void main(String[] args) {
        // Test Case 1: 4-directional standard
        char[][] grid1 = {
            {'1', '1', '1', '1', '0'},
            {'1', '1', '0', '1', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '0', '0', '0'}
        };
        System.out.println("Test 1 (4-Dir DFS): " + numIslandsDFS(grid1)); // Expected: 1
        System.out.println("Test 1 (4-Dir BFS): " + numIslandsBFS(grid1)); // Expected: 1

        // Test Case 2: Multi-islands 4-directional
        char[][] grid2 = {
            {'1', '1', '0', '0', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '1', '0', '0'},
            {'0', '0', '0', '1', '1'}
        };
        System.out.println("Test 2 (4-Dir DFS): " + numIslandsDFS(grid2)); // Expected: 3
        System.out.println("Test 2 (4-Dir BFS): " + numIslandsBFS(grid2)); // Expected: 3
        System.out.println("Test 2 (8-Dir DFS GFG): " + numIslands8Dir(grid2)); // Expected: 1 (diagonals connect!)
    }
}
