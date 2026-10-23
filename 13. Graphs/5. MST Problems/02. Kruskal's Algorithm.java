/* QUESTION:

Given a weighted, undirected and connected graph of V vertices and E edges, the task is to find the sum of weights of the edges of the Minimum Spanning Tree.

Example:

Input: 
3 3
0 1 5
1 2 3
0 2 1

Output:
4
Explanation:

The Spanning Tree resulting in a weight
of 4 is shown above.

APPROACH:
- We will use Kruskal's algorithm to find the Minimum Spanning Tree (MST) of the graph.
- First, we will sort all the edges in non-decreasing order based on their weights.
- Then, we will initialize a Disjoint Set data structure to keep track of connected components.
- We will start adding edges one by one from the sorted list of edges to the MST.
- Before adding an edge (u, v) to the MST, we will check if u and v belong to the same connected component using the Disjoint Set data structure. If they do not belong to the same connected component, we will add the edge to the MST and union the connected components of u and v.
- We will repeat this process until all vertices are added to the MST.

COMPLEXITY ANALYSIS:
- The time complexity of Kruskal's algorithm is O(E log E), where E is the number of edges, due to the sorting step.
- The space complexity is O(V) for the Disjoint Set data structure and other data structures.

CODE:
*/

import java.util.*;
import java.io.*;

// CODE:-
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
