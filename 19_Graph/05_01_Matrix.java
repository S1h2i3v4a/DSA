/**
 * Problem Name: 01 Matrix (Distance to Nearest 0)
 * Platform: LeetCode (LC 542)
 * Difficulty: Medium
 * 
 * Time Complexity: O(M * N) where M is rows and N is columns
 * Space Complexity: O(M * N) for BFS queue and distance matrix
 * 
 * Approach:
 * Multi-Source BFS:
 * Instead of starting BFS from every 1 (which would be O((M*N)^2)), start multi-source BFS
 * simultaneously from all 0s!
 * 1. Initialize dist[m][n] with -1 for all cells.
 * 2. Add all cells with value 0 to a Queue and set dist[r][c] = 0.
 * 3. Run BFS level by level:
 *    - For each cell, check 4 neighbors.
 *    - If neighbor has dist == -1 (unvisited), set dist[nr][nc] = dist[r][c] + 1 and offer to queue.
 * 4. Return dist matrix.
 */

import java.util.*;

class Zero_One_Matrix {
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        int[][] dist = new int[m][n];
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    dist[i][j] = 0;
                    queue.offer(new int[]{i, j});
                } else {
                    dist[i][j] = -1; // Unvisited marker
                }
            }
        }

        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];

            for (int[] d : dirs) {
                int nr = r + d[0];
                int nc = c + d[1];

                if (nr >= 0 && nr < m && nc >= 0 && nc < n && dist[nr][nc] == -1) {
                    dist[nr][nc] = dist[r][c] + 1;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) {
        Zero_One_Matrix solver = new Zero_One_Matrix();
        int[][] mat = {
            {0, 0, 0},
            {0, 1, 0},
            {1, 1, 1}
        };

        int[][] result = solver.updateMatrix(mat);
        System.out.println("Nearest 0 distances:");
        for (int[] row : result) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
        // Expected:
        // 0 0 0
        // 0 1 0
        // 1 2 1
    }
}
