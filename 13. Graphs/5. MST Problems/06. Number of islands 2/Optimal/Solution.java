/*
QUESTION:
You are given an empty 2D binary grid of size `m x n` and an array `operators` representing positions `[r, c]` to turn into land.
Return the number of islands after each operation.

APPROACH:
- Online connected components using DSU on cell indices `r * cols + c`.
- Maintain a count of islands.
- When `[r, c]` becomes land:
  - Increment island count.
  - Check 4 adjacent cells: if any is already land, union it with `(r, c)`.
  - Record current island count.

TIME COMPLEXITY: O(K * alpha(M * N)) where K is number of operators.
SPACE COMPLEXITY: O(M * N) for DSU and grid.
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    private static class DisjointSet {
        int[] parent, size;
        DisjointSet(int n) {
            parent = new int[n];
            size = new int[n];
            Arrays.fill(parent, -1);
            Arrays.fill(size, 1);
        }
        int find(int i) {
            if (parent[i] == i) return i;
            return parent[i] = find(parent[i]);
        }
        boolean union(int u, int v) {
            int rootU = find(u);
            int rootV = find(v);
            if (rootU == rootV) return false;
            if (size[rootU] < size[rootV]) {
                parent[rootU] = rootV;
                size[rootV] += size[rootU];
            } else {
                parent[rootV] = rootU;
                size[rootU] += size[rootV];
            }
            return true;
        }
    }

    public List<Integer> numOfIslands(int rows, int cols, int[][] operators) {
        DisjointSet ds = new DisjointSet(rows * cols);
        boolean[][] isLand = new boolean[rows][cols];
        List<Integer> result = new ArrayList<>();
        int count = 0;

        int[] dR = {-1, 1, 0, 0};
        int[] dC = {0, 0, -1, 1};

        for (int[] op : operators) {
            int r = op[0], c = op[1];
            if (isLand[r][c]) {
                result.add(count);
                continue;
            }

            isLand[r][c] = true;
            int id = r * cols + c;
            ds.parent[id] = id;
            count++;

            for (int k = 0; k < 4; k++) {
                int nr = r + dR[k];
                int nc = c + dC[k];
                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && isLand[nr][nc]) {
                    int neighborId = nr * cols + nc;
                    if (ds.union(id, neighborId)) {
                        count--;
                    }
                }
            }
            result.add(count);
        }
        return result;
    }
}
