/*
QUESTION:
Find the sum of weights of the edges of the Minimum Spanning Tree using Kruskal's Algorithm.

APPROACH:
- Sort all edges in non-decreasing order of their weights.
- Initialize Disjoint Set Union (DSU) of size `V`.
- For each edge `(u, v, wt)`:
  - If `find(u) != find(v)`: union them, add `wt` to MST sum.

TIME COMPLEXITY: O(E log E + E * alpha(V)).
SPACE COMPLEXITY: O(V + E).
*/

import java.util.Collections;
import java.util.List;

class Solution {
    static class Edge implements Comparable<Edge> {
        int src, dest, weight;
        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    private static class DisjointSet {
        int[] parent, rank;
        DisjointSet(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }
        int find(int i) {
            if (parent[i] == i) return i;
            return parent[i] = find(parent[i]);
        }
        boolean union(int u, int v) {
            int rootU = find(u);
            int rootV = find(v);
            if (rootU == rootV) return false;
            if (rank[rootU] < rank[rootV]) {
                parent[rootU] = rootV;
            } else if (rank[rootU] > rank[rootV]) {
                parent[rootV] = rootU;
            } else {
                parent[rootV] = rootU;
                rank[rootU]++;
            }
            return true;
        }
    }

    public static int spanningTreeKruskal(int V, List<Edge> edges) {
        Collections.sort(edges);
        DisjointSet ds = new DisjointSet(V);
        int mstWeight = 0;

        for (Edge edge : edges) {
            if (ds.union(edge.src, edge.dest)) {
                mstWeight += edge.weight;
            }
        }
        return mstWeight;
    }
}
