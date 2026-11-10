/*
QUESTION:
You are given an `n x n` integer matrix `grid` where each term represents the elevation at that point.
At time `t`, the depth of the water everywhere is `t`. You can swim anywhere with elevation `<= t`.
Return the least time until you can reach the bottom right square `(n - 1, n - 1)` if you start at `(0, 0)`.

APPROACH:
- Binary Search on time `t` between `0` and `n * n - 1`.
- For each `mid`, run BFS from `(0, 0)` to check if `(n - 1, n - 1)` can be reached using only cells with `grid[r][c] <= mid`.

TIME COMPLEXITY: O(N^2 log(N^2)).
SPACE COMPLEXITY: O(N^2) for BFS visited.
*/

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    private boolean canReach(int[][] grid, int t, int n) {
        if (grid[0][0] > t) return false;
        boolean[][] visited = new boolean[n][n];
        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{0, 0});
        visited[0][0] = true;

        int[] dR = {-1, 1, 0, 0};
        int[] dC = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0], c = curr[1];
            if (r == n - 1 && c == n - 1) return true;

            for (int k = 0; k < 4; k++) {
                int nr = r + dR[k];
                int nc = c + dC[k];
                if (nr >= 0 && nr < n && nc >= 0 && nc < n && !visited[nr][nc] && grid[nr][nc] <= t) {
                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
        return false;
    }

    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int low = grid[0][0], high = n * n - 1;
        int ans = high;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canReach(grid, mid, n)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }
}
