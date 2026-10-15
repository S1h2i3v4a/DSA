/**
 * Problem Name: Articulation Point - I (Cut Vertices)
 * Platform: GeeksforGeeks / Standard Data Structure & Algorithms
 * Difficulty: Hard
 *
 * Time Complexity: O(V + E) where V is vertices and E is edges
 * Space Complexity: O(V + E) for adjacency list, tin/low/isArticulation arrays, and recursion stack
 *
 * Approach:
 *   - An articulation point (or cut vertex) is a vertex whose removal increases the number
 *     of connected components in the graph.
 *   - We use Tarjan's DFS Algorithm with discovery time `tin[]` and lowest reachable time `low[]`:
 *     1. If a node `u` is the ROOT of the DFS tree (p == -1):
 *        It is an articulation point if and only if it has MORE THAN 1 child in the DFS tree (`children > 1`).
 *     2. If a node `u` is NOT the root of the DFS tree (p != -1):
 *        It is an articulation point if for any DFS child `v`, `low[v] >= tin[u]`.
 *        This signifies that child subtree `v` cannot reach any ancestor strictly above `u`
 *        without traversing through `u`.
 *   - Use an `isArticulation[]` boolean array to prevent duplicate entries of vertices that satisfy
 *     the cut-vertex condition across multiple children.
 */

import java.util.ArrayList;

class ArticulationPoint {

    private static int timer;

    public static ArrayList<Integer> articulationPoints(int V, ArrayList<ArrayList<Integer>> adj) {
        int[] tin = new int[V];
        int[] low = new int[V];
        boolean[] visited = new boolean[V];
        boolean[] isArticulation = new boolean[V];
        timer = 0;

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs(i, -1, adj, visited, tin, low, isArticulation);
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if (isArticulation[i]) {
                result.add(i);
            }
        }

        if (result.isEmpty()) {
            result.add(-1);
        }

        return result;
    }

    private static void dfs(int u, int p, ArrayList<ArrayList<Integer>> adj, boolean[] visited,
                            int[] tin, int[] low, boolean[] isArticulation) {
        visited[u] = true;
        tin[u] = low[u] = ++timer;
        int children = 0;

        for (int v : adj.get(u)) {
            if (v == p) {
                continue; // Skip direct parent edge
            }

            if (visited[v]) {
                // Back-edge to an ancestor
                low[u] = Math.min(low[u], tin[v]);
            } else {
                // Forward tree-edge
                children++;
                dfs(v, u, adj, visited, tin, low, isArticulation);
                low[u] = Math.min(low[u], low[v]);

                // Case 1: Non-root vertex
                if (p != -1 && low[v] >= tin[u]) {
                    isArticulation[u] = true;
                }
            }
        }

        // Case 2: Root of DFS tree
        if (p == -1 && children > 1) {
            isArticulation[u] = true;
        }
    }

    public static void main(String[] args) {
        // Test Case 1:
        // V = 5, edges: (0, 1), (1, 4), (2, 4), (2, 3), (3, 4)
        // Articulation points: [1, 4]
        int V1 = 5;
        ArrayList<ArrayList<Integer>> adj1 = new ArrayList<>();
        for (int i = 0; i < V1; i++) adj1.add(new ArrayList<>());

        int[][] edges1 = {{0, 1}, {1, 4}, {2, 4}, {2, 3}, {3, 4}};
        for (int[] e : edges1) {
            adj1.get(e[0]).add(e[1]);
            adj1.get(e[1]).add(e[0]);
        }

        System.out.println("Test 1 Articulation Points: " + articulationPoints(V1, adj1)); // [1, 4]

        // Test Case 2: Simple cycle (no articulation points)
        // V = 4, edges: (0, 1), (1, 2), (2, 3), (3, 0)
        // Expected: [-1]
        int V2 = 4;
        ArrayList<ArrayList<Integer>> adj2 = new ArrayList<>();
        for (int i = 0; i < V2; i++) adj2.add(new ArrayList<>());

        int[][] edges2 = {{0, 1}, {1, 2}, {2, 3}, {3, 0}};
        for (int[] e : edges2) {
            adj2.get(e[0]).add(e[1]);
            adj2.get(e[1]).add(e[0]);
        }

        System.out.println("Test 2 Articulation Points: " + articulationPoints(V2, adj2)); // [-1]
    }
}
