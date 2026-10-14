/**
 * Problem Name: Number of Operations to Make Network Connected
 * Platform: LeetCode (LC 1319) / GeeksforGeeks
 * Difficulty: Medium
 *
 * Time Complexity:
 *   - DSU Approach: O(V + E * alpha(V)) where V = n and E = connections.length
 *   - DFS Approach: O(V + E)
 * Space Complexity: O(V) for parent/rank arrays or adjacency list and visited array
 *
 * Approach:
 *   - To connect n computers into a single network, we require at least n - 1 cables.
 *     If connections.length < n - 1, it is impossible, return -1 immediately.
 *   - If we have >= n - 1 cables, there are guaranteed to be enough redundant cables
 *     to bridge all disjoint components.
 *   - Count the number of connected components C in the network.
 *   - The minimum number of cable operations required to connect C disjoint components is (C - 1).
 *   - We provide two approaches:
 *     1. Disjoint Set Union (DSU) with Path Compression and Union by Size/Rank.
 *     2. Breadth-First / Depth-First Search traversal.
 */

import java.util.ArrayList;
import java.util.List;

class NumberOfOperationsToMakeNetworkConnected {

    // ==========================================
    // Approach 1: Disjoint Set Union (DSU)
    // ==========================================
    static class DisjointSet {
        int[] parent;
        int[] size;
        int components;

        public DisjointSet(int n) {
            parent = new int[n];
            size = new int[n];
            components = n;
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

        public boolean union(int u, int v) {
            int rootU = find(u);
            int rootV = find(v);
            if (rootU == rootV) {
                return false; // Redundant connection / already connected
            }
            if (size[rootU] < size[rootV]) {
                parent[rootU] = rootV;
                size[rootV] += size[rootU];
            } else {
                parent[rootV] = rootU;
                size[rootU] += size[rootV];
            }
            components--;
            return true;
        }
    }

    public static int makeConnectedDSU(int n, int[][] connections) {
        if (connections.length < n - 1) {
            return -1; // Insufficient cables to connect all n computers
        }

        DisjointSet ds = new DisjointSet(n);
        int extraCables = 0;

        for (int[] edge : connections) {
            if (!ds.union(edge[0], edge[1])) {
                extraCables++;
            }
        }

        // ds.components gives the number of separate components
        int componentsNeeded = ds.components - 1;
        return (extraCables >= componentsNeeded) ? componentsNeeded : -1;
    }

    // ==========================================
    // Approach 2: Depth-First Search (DFS)
    // ==========================================
    public static int makeConnectedDFS(int n, int[][] connections) {
        if (connections.length < n - 1) {
            return -1;
        }

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : connections) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        int components = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                components++;
                dfs(i, adj, visited);
            }
        }

        return components - 1;
    }

    private static void dfs(int node, List<List<Integer>> adj, boolean[] visited) {
        visited[node] = true;
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, adj, visited);
            }
        }
    }

    public static void main(String[] args) {
        // Test Case 1:
        // n = 4, connections = [[0,1],[0,2],[1,2]]
        // Expected: 1
        int n1 = 4;
        int[][] connections1 = {{0, 1}, {0, 2}, {1, 2}};
        System.out.println("Test 1 (DSU): " + makeConnectedDSU(n1, connections1)); // 1
        System.out.println("Test 1 (DFS): " + makeConnectedDFS(n1, connections1)); // 1

        // Test Case 2:
        // n = 6, connections = [[0,1],[0,2],[0,3],[1,2],[1,3]]
        // Expected: 2
        int n2 = 6;
        int[][] connections2 = {{0, 1}, {0, 2}, {0, 3}, {1, 2}, {1, 3}};
        System.out.println("Test 2 (DSU): " + makeConnectedDSU(n2, connections2)); // 2
        System.out.println("Test 2 (DFS): " + makeConnectedDFS(n2, connections2)); // 2

        // Test Case 3:
        // n = 6, connections = [[0,1],[0,2],[0,3],[1,2]]
        // Expected: -1 (Total cables = 4 < 5)
        int n3 = 6;
        int[][] connections3 = {{0, 1}, {0, 2}, {0, 3}, {1, 2}};
        System.out.println("Test 3 (DSU): " + makeConnectedDSU(n3, connections3)); // -1
        System.out.println("Test 3 (DFS): " + makeConnectedDFS(n3, connections3)); // -1
    }
}
