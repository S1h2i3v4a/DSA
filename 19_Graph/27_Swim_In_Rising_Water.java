/**
 * Problem Name: Swim in Rising Water
 * Platform: LeetCode (LC 778) / GeeksforGeeks
 * Difficulty: Hard
 *
 * Time Complexity:
 *   - Approach 1 (PriorityQueue / Dijkstra's Variant): O(N^2 * log(N)) where N is grid dimension
 *   - Approach 2 (Binary Search + BFS/DFS): O(N^2 * log(maxElevation))
 * Space Complexity: O(N^2) for distance/visited matrix and priority queue / recursion stack
 *
 * Approach:
 *   - We need to find a path from (0, 0) to (N-1, N-1) such that the maximum elevation
 *     encountered along the path is minimized.
 *   - Approach 1 (Modified Dijkstra):
 *     Treat cell elevations as edge weights where cost(u -> v) = max(elevation(u), elevation(v)).
 *     Use a Min-Heap (PriorityQueue) tracking (time, row, col), always expanding the cell
 *     with the lowest water level required. When reaching (N-1, N-1), the current time is optimal.
 *   - Approach 2 (Binary Search on Answer):
 *     Binary search the answer in range [max(grid[0][0], grid[N-1][N-1]), N*N - 1].
 *     For each mid, verify reachability from (0, 0) to (N-1, N-1) using BFS/DFS considering
 *     only cells with elevation <= mid.
 */

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
