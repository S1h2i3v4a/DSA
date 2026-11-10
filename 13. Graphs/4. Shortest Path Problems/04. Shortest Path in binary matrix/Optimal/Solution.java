/*
QUESTION:
Given an `n x n` binary matrix `grid`, return the length of the shortest clear path in the matrix.
A clear path is a path from top-left (0, 0) to bottom-right (n - 1, n - 1) such that all visited cells are 0 and 8-directionally connected.

APPROACH:
- Standard 8-directional BFS since each step has equal weight 1.
- If `grid[0][0] != 0` or `grid[n-1][n-1] != 0`, return -1.
- Queue stores `[r, c, pathLength]`.
- Mark cells as visited by setting `grid[r][c] = 1`.

TIME COMPLEXITY: O(N * N) visiting each cell once.
SPACE COMPLEXITY: O(N * N) for BFS queue.
*/

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if (grid[0][0] != 0 || grid[n - 1][n - 1] != 0) return -1;
        if (n == 1) return 1;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 1});
        grid[0][0] = 1;

        int[] dR = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dC = {-1, 0, 1, -1, 1, -1, 0, 1};

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0], c = curr[1], len = curr[2];

            if (r == n - 1 && c == n - 1) return len;

            for (int k = 0; k < 8; k++) {
                int nr = r + dR[k];
                int nc = c + dC[k];

                if (nr >= 0 && nr < n && nc >= 0 && nc < n && grid[nr][nc] == 0) {
                    grid[nr][nc] = 1;
                    queue.offer(new int[]{nr, nc, len + 1});
                }
            }
        }
        return -1;
    }
}
