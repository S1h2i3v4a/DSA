/*
QUESTION:
Find least time to reach `(n - 1, n - 1)` using Modified Dijkstra's Algorithm.

APPROACH:
- Min-Heap storing `(maxElevation, r, c)`.
- Start with `(grid[0][0], 0, 0)`.
- At each step, pop the cell with smallest required water level.
- When reaching `(n - 1, n - 1)`, the water level is the answer.

TIME COMPLEXITY: O(N^2 log N).
SPACE COMPLEXITY: O(N^2) for PriorityQueue and visited array.
*/

import java.util.PriorityQueue;

class Solution {
    private static class Cell implements Comparable<Cell> {
        int time, r, c;
        Cell(int time, int r, int c) {
            this.time = time;
            this.r = r;
            this.c = c;
        }
        public int compareTo(Cell other) {
            return Integer.compare(this.time, other.time);
        }
    }

    public int swimInWater(int[][] grid) {
        int n = grid.length;
        boolean[][] visited = new boolean[n][n];
        PriorityQueue<Cell> pq = new PriorityQueue<>();

        pq.offer(new Cell(grid[0][0], 0, 0));
        visited[0][0] = true;

        int[] dR = {-1, 1, 0, 0};
        int[] dC = {0, 0, -1, 1};

        while (!pq.isEmpty()) {
            Cell curr = pq.poll();
            int t = curr.time;
            int r = curr.r, c = curr.c;

            if (r == n - 1 && c == n - 1) return t;

            for (int k = 0; k < 4; k++) {
                int nr = r + dR[k];
                int nc = c + dC[k];

                if (nr >= 0 && nr < n && nc >= 0 && nc < n && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    pq.offer(new Cell(Math.max(t, grid[nr][nc]), nr, nc));
                }
            }
        }
        return 0;
    }
}
