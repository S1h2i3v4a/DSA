/*
QUESTION:
You are given an `n x n` binary matrix `grid`. You are allowed to change at most one 0 to be 1.
Return the size of the largest island in `grid` after applying this operation.

APPROACH:
- Step 1: Group all connected 1s into island components using DSU, tracking component sizes.
- Step 2: For each 0-cell in grid:
  - Collect unique adjacent component roots.
  - Potential size = `1 + sum of sizes of unique adjacent components`.
  - Maximize over all 0-cells.

TIME COMPLEXITY: O(N^2).
SPACE COMPLEXITY: O(N^2) for DSU.
*/

import java.util.HashSet;
import java.util.Set;

class Solution {
    private static class DisjointSet {
        int[] parent, size;
        DisjointSet(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }
        int find(int i) {
            if (parent[i] == i) return i;
            return parent[i] = find(parent[i]);
        }
        void union(int u, int v) {
            int rootU = find(u);
            int rootV = find(v);
            if (rootU != rootV) {
                if (size[rootU] < size[rootV]) {
                    parent[rootU] = rootV;
                    size[rootV] += size[rootU];
                } else {
                    parent[rootV] = rootU;
                    size[rootU] += size[rootV];
                }
            }
        }
    }

    public int largestIsland(int[][] grid) {
        int n = grid.length;
        DisjointSet ds = new DisjointSet(n * n);
        int[] dR = {-1, 1, 0, 0};
        int[] dC = {0, 0, -1, 1};

        // Step 1: Union adjacent 1s
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 1) {
                    for (int k = 0; k < 4; k++) {
                        int nr = r + dR[k];
                        int nc = c + dC[k];
                        if (nr >= 0 && nr < n && nc >= 0 && nc < n && grid[nr][nc] == 1) {
                            ds.union(r * n + c, nr * n + nc);
                        }
                    }
                }
            }
        }

        // Step 2: Evaluate each 0
        int maxIsland = 0;
        for (int i = 0; i < n * n; i++) {
            maxIsland = Math.max(maxIsland, ds.size[ds.find(i)]);
        }

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 0) {
                    Set<Integer> uniqueComponents = new HashSet<>();
                    for (int k = 0; k < 4; k++) {
                        int nr = r + dR[k];
                        int nc = c + dC[k];
                        if (nr >= 0 && nr < n && nc >= 0 && nc < n && grid[nr][nc] == 1) {
                            uniqueComponents.add(ds.find(nr * n + nc));
                        }
                    }
                    int totalSize = 1;
                    for (int root : uniqueComponents) {
                        totalSize += ds.size[root];
                    }
                    maxIsland = Math.max(maxIsland, totalSize);
                }
            }
        }
        return maxIsland;
    }
}
