/*
QUESTION:
You are a hiker preparing for an upcoming hike. You are given `heights`, a 2D array of size `rows x columns`.
Find the minimum effort required to travel from the top-left to the bottom-right cell.
The effort of a path is the maximum absolute difference in heights between two consecutive cells of the path.

APPROACH:
- Modified Dijkstra's Algorithm using a Min-Heap.
- `effort[r][c]` stores the minimum effort to reach cell `(r, c)`.
- Heap stores `(effort, r, c)`.
- For neighbor `(nr, nc)`: `newEffort = Math.max(effort, |heights[nr][nc] - heights[r][c]|)`.
- If `newEffort < effort[nr][nc]`, update and push into heap.

TIME COMPLEXITY: O(R * C log(R * C)).
SPACE COMPLEXITY: O(R * C).
*/

import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    private static class Cell implements Comparable<Cell> {
        int effort, r, c;
        Cell(int effort, int r, int c) {
            this.effort = effort;
            this.r = r;
            this.c = c;
        }
        public int compareTo(Cell other) {
            return Integer.compare(this.effort, other.effort);
        }
    }

    public int minimumEffortPath(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        int[][] effort = new int[m][n];
        for (int[] row : effort) Arrays.fill(row, Integer.MAX_VALUE);

        PriorityQueue<Cell> pq = new PriorityQueue<>();
        effort[0][0] = 0;
        pq.offer(new Cell(0, 0, 0));

        int[] dR = {-1, 1, 0, 0};
        int[] dC = {0, 0, -1, 1};

        while (!pq.isEmpty()) {
            Cell curr = pq.poll();
            int eff = curr.effort;
            int r = curr.r, c = curr.c;

            if (r == m - 1 && c == n - 1) return eff;
            if (eff > effort[r][c]) continue;

            for (int k = 0; k < 4; k++) {
                int nr = r + dR[k];
                int nc = c + dC[k];

                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    int nextEffort = Math.max(eff, Math.abs(heights[nr][nc] - heights[r][c]));
                    if (nextEffort < effort[nr][nc]) {
                        effort[nr][nc] = nextEffort;
                        pq.offer(new Cell(nextEffort, nr, nc));
                    }
                }
            }
        }
        return 0;
    }
}
