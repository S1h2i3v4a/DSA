/**
 * Problem Name: Most Stones Removed with Same Row or Column
 * Platform: LeetCode (LC 947) / GeeksforGeeks
 * Difficulty: Medium
 *
 * Time Complexity:
 *   - Approach 1 (DSU on Row and Column Coordinates): O(N * alpha(maxCoord)) ~ O(N)
 *   - Approach 2 (DFS on Stone Indices): O(N^2) or O(N) with row/column index maps
 * Space Complexity: O(maxRow + maxCol) or O(N) for parent/visited structures
 *
 * Approach:
 *   - If two stones share either a row or a column, they belong to the same connected component.
 *   - In any connected component of size S, we can greedily remove S - 1 stones by removing
 *     leaves of a spanning tree one by one, leaving exactly 1 stone remaining.
 *   - Therefore:
 *       Max Stones Removed = (Total Stones) - (Number of Connected Components)
 *   - Using Disjoint Set Union (DSU):
 *     Treat each row and each column as a node in a bipartite graph.
 *     To distinguish columns from rows, map column `c` to `c + 10001` (or offset `c + maxRow + 1`).
 *     For each stone at (r, c), union(r, c + offset).
 *     The number of components is the number of distinct roots among all present coordinates.
 */

import java.util.*;

class MostStonesRemovedWithSameRowOrColumn {

    // ==========================================
    // Approach 1: DSU on Coordinates (Row & Col)
    // ==========================================
    static class DisjointSet {
        int[] parent;
        int[] size;

        public DisjointSet(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int node) {
            if (node == parent[node]) {
                return node;
            }
            return parent[node] = find(parent[node]); // Path compression
        }

        public void union(int u, int v) {
            int rootU = find(u);
            int rootV = find(v);
            if (rootU == rootV) return;

            if (size[rootU] < size[rootV]) {
                parent[rootU] = rootV;
                size[rootV] += size[rootU];
            } else {
                parent[rootV] = rootU;
                size[rootU] += size[rootV];
            }
        }
    }

    public static int removeStonesDSU(int[][] stones) {
        int maxRow = 0;
        int maxCol = 0;
        for (int[] stone : stones) {
            maxRow = Math.max(maxRow, stone[0]);
            maxCol = Math.max(maxCol, stone[1]);
        }

        int colOffset = maxRow + 1;
        DisjointSet ds = new DisjointSet(maxRow + maxCol + 2);
        Set<Integer> stoneNodes = new HashSet<>();

        for (int[] stone : stones) {
            int rowNode = stone[0];
            int colNode = stone[1] + colOffset;
            ds.union(rowNode, colNode);
            stoneNodes.add(rowNode);
            stoneNodes.add(colNode);
        }

        // Count number of unique component representatives
        int components = 0;
        for (int node : stoneNodes) {
            if (ds.find(node) == node) {
                components++;
            }
        }

        return stones.length - components;
    }

    // ==========================================
    // Approach 2: DFS on Stone Graph
    // ==========================================
    public static int removeStonesDFS(int[][] stones) {
        int n = stones.length;
        boolean[] visited = new boolean[n];
        int components = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                components++;
                dfs(i, stones, visited, n);
            }
        }

        return n - components;
    }

    private static void dfs(int curr, int[][] stones, boolean[] visited, int n) {
        visited[curr] = true;
        for (int next = 0; next < n; next++) {
            if (!visited[next]) {
                // If sharing the same row or column
                if (stones[curr][0] == stones[next][0] || stones[curr][1] == stones[next][1]) {
                    dfs(next, stones, visited, n);
                }
            }
        }
    }

    public static void main(String[] args) {
        // Test Case 1:
        // stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
        // Expected: 5
        int[][] stones1 = {{0, 0}, {0, 1}, {1, 0}, {1, 2}, {2, 1}, {2, 2}};
        System.out.println("Test 1 (DSU): " + removeStonesDSU(stones1)); // 5
        System.out.println("Test 1 (DFS): " + removeStonesDFS(stones1)); // 5

        // Test Case 2:
        // stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
        // Expected: 3
        int[][] stones2 = {{0, 0}, {0, 2}, {1, 1}, {2, 0}, {2, 2}};
        System.out.println("Test 2 (DSU): " + removeStonesDSU(stones2)); // 3
        System.out.println("Test 2 (DFS): " + removeStonesDFS(stones2)); // 3

        // Test Case 3:
        // stones = [[0,0]]
        // Expected: 0
        int[][] stones3 = {{0, 0}};
        System.out.println("Test 3 (DSU): " + removeStonesDSU(stones3)); // 0
        System.out.println("Test 3 (DFS): " + removeStonesDFS(stones3)); // 0
    }
}
