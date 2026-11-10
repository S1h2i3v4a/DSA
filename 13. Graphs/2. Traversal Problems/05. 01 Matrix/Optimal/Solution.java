/*
QUESTION:
Given an `m x n` binary matrix `mat`, return the distance of the nearest 0 for each cell.
The distance between two adjacent cells is 1.

APPROACH:
- Multi-source BFS starting from all 0s.
- Initialize `dist[m][n]` to -1.
- Enqueue all cells with `mat[i][j] == 0`, set `dist[i][j] = 0`.
- BFS outwards in 4 directions updating `dist[nr][nc] = dist[r][c] + 1`.

TIME COMPLEXITY: O(M * N).
SPACE COMPLEXITY: O(M * N) for BFS queue.
*/

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[][] dist = new int[m][n];
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    dist[i][j] = 0;
                    queue.offer(new int[]{i, j});
                } else {
                    dist[i][j] = -1;
                }
            }
        }

        int[] dRow = {-1, 1, 0, 0};
        int[] dCol = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0], c = curr[1];

            for (int k = 0; k < 4; k++) {
                int nr = r + dRow[k];
                int nc = c + dCol[k];

                if (nr >= 0 && nr < m && nc >= 0 && nc < n && dist[nr][nc] == -1) {
                    dist[nr][nc] = dist[r][c] + 1;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
        return dist;
    }
}
