/*QUESTION:

You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns, where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0), and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed). You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.

A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.

Return the minimum effort required to travel from the top-left cell to the bottom-right cell.

Example:

Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
Output: 2
Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.

APPROACH:
- We can use Dijkstra's algorithm to find the minimum effort path from the top-left cell to the bottom-right cell.
- We will maintain a priority queue to keep track of cells with the minimum effort needed.
- Start from the top-left cell with effort 0 and explore neighboring cells in all four directions.
- Calculate the effort required to move to a neighboring cell and update the effort if it is lower than the current value.
- Continue this process until we reach the bottom-right cell.

COMPLEXITY ANALYSIS:
- Since we are using Dijkstra's algorithm, the time complexity is O((N*M) * log(N*M)), where N is the number of rows and M is the number of columns in the 2D array.
- The space complexity is O(N*M) to store the distance values.

CODE:
*/

import java.util.*;
import java.io.*;

// CODE:-
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
