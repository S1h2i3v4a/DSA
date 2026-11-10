/*
QUESTION:
You are given an `m x n` grid where each cell can have values: 0 (empty), 1 (fresh orange), 2 (rotten orange).
Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
Return the minimum number of minutes that must elapse until no cell has a fresh orange. If impossible, return -1.

APPROACH:
- Multi-source BFS using a Queue.
- Count fresh oranges and enqueue all initially rotten oranges with time = 0.
- For each rotten orange, rot adjacent fresh oranges, decrement fresh count, and enqueue with `time + 1`.
- If fresh count == 0 return max time, else -1.

TIME COMPLEXITY: O(M * N) visiting each cell once.
SPACE COMPLEXITY: O(M * N) for BFS queue.
*/

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int orangesRotting(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int freshCount = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j, 0});
                } else if (grid[i][j] == 1) {
                    freshCount++;
                }
            }
        }

        if (freshCount == 0) return 0;

        int minutes = 0;
        int[] dRow = {-1, 1, 0, 0};
        int[] dCol = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0], c = curr[1], t = curr[2];
            minutes = Math.max(minutes, t);

            for (int k = 0; k < 4; k++) {
                int nr = r + dRow[k];
                int nc = c + dCol[k];

                if (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] == 1) {
                    grid[nr][nc] = 2;
                    freshCount--;
                    queue.offer(new int[]{nr, nc, t + 1});
                }
            }
        }

        return freshCount == 0 ? minutes : -1;
    }
}
