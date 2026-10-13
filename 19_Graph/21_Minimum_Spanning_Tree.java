/**
 * Problem Name: Minimum Spanning Tree (Prim's & Kruskal's Algorithms)
 * Platform: GeeksforGeeks
 * Difficulty: Medium
 * 
 * Time Complexity:
 *   - Prim's Algorithm: O(E * log V)
 *   - Kruskal's Algorithm: O(E * log E + E * alpha(V))
 * Space Complexity: O(V + E) for PriorityQueue / DSU structures
 * 
 * Approaches:
 * 1. Prim's Algorithm:
 *    - Greedy approach growing MST one vertex at a time.
 *    - Uses a Min-Heap storing Pair(weight, node).
 *    - Poll minimum weight edge connecting an unvisited vertex, add weight to MST sum,
 *      mark vertex visited, and push its unvisited neighbors to PQ.
 * 
 * 2. Kruskal's Algorithm:
 *    - Greedy approach selecting edges globally.
 *    - Uses Disjoint Set Union (DSU) with Path Compression and Union by Rank.
 *    - Sort all edges by weight ascending.
 *    - For each edge (u, v, w): if find(u) != find(v), union(u, v) and add w to MST sum.
 */

import java.util.*;

class Minimum_Spanning_Tree {

    // ==========================================
    // Approach 1: Prim's Algorithm
    // ==========================================
    private static class PrimNode implements Comparable<PrimNode> {
        int node;
        int weight;

        PrimNode(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(PrimNode other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    public int spanningTreePrim(int V, int E, List<List<int[]>> adj) {
        boolean[] visited = new boolean[V];
        PriorityQueue<PrimNode> pq = new PriorityQueue<>();

        pq.offer(new PrimNode(0, 0));
        int mstSum = 0;

        while (!pq.isEmpty()) {
            PrimNode curr = pq.poll();
            int u = curr.node;
            int w = curr.weight;

            if (visited[u]) continue;

            visited[u] = true;
            mstSum += w;

            for (int[] edge : adj.get(u)) {
                int v = edge[0];
                int weight = edge[1];

                if (!visited[v]) {
                    pq.offer(new PrimNode(v, weight));
                }
            }
        }

        return mstSum;
    }

    // ==========================================
    // Approach 2: Kruskal's Algorithm
    // ==========================================
    private static class DisjointSet {
        int[] parent;
        int[] rank;

        DisjointSet(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        int find(int i) {
            if (parent[i] == i) return i;
            return parent[i] = find(parent[i]); // Path compression
        }

        boolean union(int u, int v) {
            int rootU = find(u);
            int rootV = find(v);
            if (rootU == rootV) return false;

            if (rank[rootU] < rank[rootV]) {
                parent[rootU] = rootV;
            } else if (rank[rootV] < rank[rootU]) {
                parent[rootV] = rootU;
            } else {
                parent[rootV] = rootU;
                rank[rootU]++;
            }
            return true;
        }
    }

    private static class Edge implements Comparable<Edge> {
        int u, v, weight;

        Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    public int spanningTreeKruskal(int V, List<Edge> edges) {
        Collections.sort(edges);
        DisjointSet dsu = new DisjointSet(V);
        int mstSum = 0;

        for (Edge e : edges) {
            if (dsu.union(e.u, e.v)) {
                mstSum += e.weight;
            }
        }

        return mstSum;
    }

    public static void main(String[] args) {
        Minimum_Spanning_Tree solver = new Minimum_Spanning_Tree();
        int V = 5;

        // Build adjacency list for Prim
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());

        List<Edge> edges = new ArrayList<>();

        int[][] rawEdges = {
            {0, 1, 2}, {0, 3, 6}, {1, 2, 3}, {1, 3, 8},
            {1, 4, 5}, {2, 4, 7}, {3, 4, 9}
        };

        for (int[] e : rawEdges) {
            adj.get(e[0]).add(new int[]{e[1], e[2]});
            adj.get(e[1]).add(new int[]{e[0], e[2]});
            edges.add(new Edge(e[0], e[1], e[2]));
        }

        System.out.println("MST Weight Sum (Prim's):    " + solver.spanningTreePrim(V, rawEdges.length, adj));     // Expected: 16
        System.out.println("MST Weight Sum (Kruskal's): " + solver.spanningTreeKruskal(V, edges));               // Expected: 16
    }
}
