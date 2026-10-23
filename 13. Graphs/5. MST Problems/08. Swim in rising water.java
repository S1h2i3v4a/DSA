/*
QUESTION:-
You are given an n x n integer matrix grid where each value grid[i][j] represents the elevation at that point (i, j).
The rain starts to fall. At time t, the depth of the water everywhere is t. You can swim from a square to another 4-directionally adjacent square if and only if the elevation of both squares individually are at most t. You can swim infinite distances in zero time. Of course, you must stay within the boundaries of the grid during your swim.

Return the least time until you can reach the bottom right square (n - 1, n - 1) if you start at the top left square (0, 0).

Approach:
1. We use a priority queue (min heap) to keep track of the cells in increasing order of their elevations.
2. Start from the top left cell (0, 0) and add it to the priority queue with its elevation as the key.
3. Mark this cell as visited.
4. While the priority queue is not empty, pop the cell with the minimum elevation.
5. Update the answer with the maximum of the current elevation and the answer so far.
6. Check the 4-directionally adjacent cells of the current cell.
7. If the adjacent cell is within the boundaries and not visited, add it to the priority queue with its elevation as the key.
8. Mark the adjacent cell as visited.
9. Continue the process until we reach the bottom right cell (n-1, n-1).
10. The answer at this point will be the least time until we can reach the bottom right cell.

Complexity Analysis:
- We need to visit all cells of the grid, so the time complexity of the solution is O(n^2).
- We use a priority queue to store at most n^2 cells, so the space complexity is also O(n^2).

CODE:-
*/

import java.util.*;
import java.io.*;

// CODE:-
import java.util.PriorityQueue;

class SwimInRisingWater {

    // ==========================================
    // Approach 1: Dijkstra's Algorithm (Min-Heap)
    // ==========================================
    public static int swimInWaterDijkstra(int[][] grid) {
        int n = grid.length;
        boolean[][] visited = new boolean[n][n];

        // PriorityQueue stores int[]{time, row, col}, sorted by time ascending
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        pq.offer(new int[]{grid[0][0], 0, 0});
        visited[0][0] = true;

        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int time = curr[0];
            int r = curr[1];
            int c = curr[2];

            // Reached destination
            if (r == n - 1 && c == n - 1) {
                return time;
            }

            for (int[] d : dirs) {
                int nr = r + d[0];
                int nc = c + d[1];

                if (nr >= 0 && nr < n && nc >= 0 && nc < n && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    int nextTime = Math.max(time, grid[nr][nc]);
                    pq.offer(new int[]{nextTime, nr, nc});
                }
            }
        }

        return -1;
    }

    // ==========================================
    // Approach 2: Binary Search + DFS
    // ==========================================
    public static int swimInWaterBinarySearch(int[][] grid) {
        int n = grid.length;
        int low = Math.max(grid[0][0], grid[n - 1][n - 1]);
        int high = n * n - 1;
        int ans = high;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            boolean[][] visited = new boolean[n][n];
            if (canReach(0, 0, mid, grid, visited, n)) {
                ans = mid;
                high = mid - 1; // Try finding a smaller feasible water level
            } else {
                low = mid + 1; // Increase water level
            }
        }

        return ans;
    }

    private static boolean canReach(int r, int c, int maxTime, int[][] grid, boolean[][] visited, int n) {
        if (r == n - 1 && c == n - 1) {
            return true;
        }

        visited[r][c] = true;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int[] d : dirs) {
            int nr = r + d[0];
            int nc = c + d[1];

            if (nr >= 0 && nr < n && nc >= 0 && nc < n && !visited[nr][nc] && grid[nr][nc] <= maxTime) {
                if (canReach(nr, nc, maxTime, grid, visited, n)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        // Test Case 1:
        // grid = [[0,2],[1,3]] -> Expected: 3
        int[][] grid1 = {
            {0, 2},
            {1, 3}
        };
        System.out.println("Test 1 (Dijkstra): " + swimInWaterDijkstra(grid1)); // 3
        System.out.println("Test 1 (Binary Search): " + swimInWaterBinarySearch(grid1)); // 3

        // Test Case 2:
        // grid = [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
        // Expected: 16
        int[][] grid2 = {
            {0, 1, 2, 3, 4},
            {24, 23, 22, 21, 5},
            {12, 13, 14, 15, 16},
            {11, 17, 18, 19, 20},
            {10, 9, 8, 7, 6}
        };
        System.out.println("Test 2 (Dijkstra): " + swimInWaterDijkstra(grid2)); // 16
        System.out.println("Test 2 (Binary Search): " + swimInWaterBinarySearch(grid2)); // 16
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
