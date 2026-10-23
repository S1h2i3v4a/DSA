/*
QUESTION:

On a 2D plane, we place n stones at some integer coordinate points. Each coordinate point may have at most one stone.

A stone can be removed if it shares either the same row or the same column as another stone that has not been removed.

Given an array stones of length n where stones[i] = [xi, yi] represents the location of the ith stone, return the largest possible number of stones that can be removed.

Example 1:

Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
Output: 5
Explanation: One way to remove 5 stones is as follows:
1. Remove stone [2,2] because it shares the same row as [2,1].
2. Remove stone [2,1] because it shares the same column as [0,1].
3. Remove stone [1,2] because it shares the same row as [1,0].
4. Remove stone [1,0] because it shares the same column as [0,0].
5. Remove stone [0,1] because it shares the same row as [0,0].
Stone [0,0] cannot be removed since it does not share a row/column with another stone still on the plane.

APPROACH:

To solve this problem, we can use the Disjoint Set data structure to group stones that share the same row or column.
- First, we find the minimum and maximum row and column values to calculate the size of the grid.
- Then, we iterate through the stones and perform the union operation for stones that share the same row or column.
- Finally, we count the number of disjoint sets and subtract it from the total number of stones to get the maximum number of stones that can be removed.

COMPLEXITY ANALYSIS:

Let n be the number of stones and m be the size of the grid.

- Building the Disjoint Set: O(n)
- Finding the number of disjoint sets: O(n)
- Overall Time Complexity: O(n)
- Space Complexity: O(n) for the Disjoint Set data structure

CODE:-
*/

import java.util.*;
import java.io.*;

// CODE:-
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
