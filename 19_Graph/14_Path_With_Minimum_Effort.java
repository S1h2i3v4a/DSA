/**
 * Problem Name: Path With Minimum Effort
 * Platform: LeetCode (LC 1631)
 * Difficulty: Medium
 * 
 * Time Complexity: O(R * C * log(R * C)) where R is rows and C is columns
 * Space Complexity: O(R * C) for effort matrix and PriorityQueue
 * 
 * Approach:
 * Dijkstra's Algorithm (Min-Heap):
 * A path's effort is defined as the maximum absolute difference between consecutive cells on the path.
 * 1. Initialize dist[R][C] matrix with Integer.MAX_VALUE, and set dist[0][0] = 0.
 * 2. Use a PriorityQueue (Min-Heap) storing Tuple(effort, r, c) sorted by effort ascending.
 * 3. Offer (0, 0, 0) to PQ.
 * 4. While PQ is not empty:
 *    - Poll (effort, r, c).
 *    - If r == R - 1 and c == C - 1, return effort (first time reached destination is optimal).
 *    - For 4 neighbors:
 *      newEffort = Math.max(effort, Math.abs(heights[nr][nc] - heights[r][c]));
 *      If newEffort < dist[nr][nc]:
 *        dist[nr][nc] = newEffort;
 *        Offer (newEffort, nr, nc) to PQ.
 * 5. Return 0.
 */

import java.util.*;

class Path_With_Minimum_Effort {
    private static class Cell implements Comparable<Cell> {
        int effort;
        int r;
        int c;

        Cell(int effort, int r, int c) {
            this.effort = effort;
            this.r = r;
            this.c = c;
        }

        @Override
        public int compareTo(Cell other) {
            return Integer.compare(this.effort, other.effort);
        }
    }

    public int minimumEffortPath(int[][] heights) {
        int rows = heights.length;
        int cols = heights[0].length;

        int[][] dist = new int[rows][cols];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dist[0][0] = 0;

        PriorityQueue<Cell> pq = new PriorityQueue<>();
        pq.offer(new Cell(0, 0, 0));

        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!pq.isEmpty()) {
            Cell curr = pq.poll();
            int effort = curr.effort;
            int r = curr.r;
            int c = curr.c;

            if (r == rows - 1 && c == cols - 1) {
                return effort;
            }

            if (effort > dist[r][c]) continue;

            for (int[] d : dirs) {
                int nr = r + d[0];
                int nc = c + d[1];

                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
                    int diff = Math.abs(heights[nr][nc] - heights[r][c]);
                    int nextEffort = Math.max(effort, diff);

                    if (nextEffort < dist[nr][nc]) {
                        dist[nr][nc] = nextEffort;
                        pq.offer(new Cell(nextEffort, nr, nc));
                    }
                }
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        Path_With_Minimum_Effort solver = new Path_With_Minimum_Effort();
        int[][] heights = {
            {1, 2, 2},
            {3, 8, 2},
            {5, 3, 5}
        };
        System.out.println("Minimum effort path: " + solver.minimumEffortPath(heights)); // Expected: 2 (1->2->2->2->5)
    }
}
