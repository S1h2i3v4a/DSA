/*
QUESTION:-
You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.
Return the size of the largest island in grid after applying this operation.
An island is a 4-directionally connected group of 1s.

Approach:
1. Create a disjoint set to represent islands and initialize it with all cells.
2. For each 1 cell, union it with its 4-directionally connected 1 cells in the disjoint set.
3. Then, traverse the grid to find each 0 cell and count the size of connected islands that can be formed by changing this 0 to 1.
4. Update the answer with the maximum island size found so far.
5. Return the maximum island size.

Complexity Analysis:
- The time complexity of creating the disjoint set is O(n^2) as we need to initialize it with all cells.
- The time complexity of the first pass (connecting 1 cells) is also O(n^2) as we traverse the entire grid once.
- The time complexity of the second pass (counting island size for 0 cells) is O(n^2) as we again traverse the entire grid.
- The time complexity of finding the maximum island size using the disjoint set is O(n^2) as we iterate through all cells.
- Overall, the time complexity of the solution is O(n^2).
- The space complexity is O(n^2) due to the disjoint set data structure.

CODE:-
*/

import java.util.*;
import java.io.*;

// CODE:-
class DisjointSet {
    int[] parent, size;
    DisjointSet(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) { parent[i] = i; size[i] = 1; }
    }
    int find(int i) {
        if (parent[i] == i) return i;
        return parent[i] = find(parent[i]);
    }
    void union(int u, int v) {
        int rootU = find(u), rootV = find(v);
        if (rootU != rootV) {
            if (size[rootU] < size[rootV]) { parent[rootU] = rootV; size[rootV] += size[rootU]; }
            else { parent[rootV] = rootU; size[rootU] += size[rootV]; }
        }
    }
}

class Solution {
    public int largestIsland(int[][] grid) {
        int n = grid.length;
        DisjointSet ds = new DisjointSet(n * n);
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 0) continue;
                for (int i = 0; i < 4; i++) {
                    int nr = r + dr[i], nc = c + dc[i];
                    if (nr >= 0 && nr < n && nc >= 0 && nc < n && grid[nr][nc] == 1) {
                        ds.union(r * n + c, nr * n + nc);
                    }
                }
            }
        }
        int max = 0;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 1) {
                    max = Math.max(max, ds.size[ds.find(r * n + c)]);
                    continue;
                }
                Set<Integer> components = new HashSet<>();
                for (int i = 0; i < 4; i++) {
                    int nr = r + dr[i], nc = c + dc[i];
                    if (nr >= 0 && nr < n && nc >= 0 && nc < n && grid[nr][nc] == 1) {
                        components.add(ds.find(nr * n + nc));
                    }
                }
                int totalSize = 1;
                for (int root : components) totalSize += ds.size[root];
                max = Math.max(max, totalSize);
            }
        }
        return max;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
