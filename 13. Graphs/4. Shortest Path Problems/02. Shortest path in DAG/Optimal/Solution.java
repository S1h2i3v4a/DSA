/*
QUESTION:
Given a Directed Acyclic Graph of N vertices from 0 to N-1 and a 2D Integer array `edges` where `edges[i] = [u, v, weight]`.
Find the shortest path from 0 to all other vertices.

APPROACH:
- Step 1: Topological sort using DFS to find linear ordering of DAG.
- Step 2: Initialize `dist` array with infinity, `dist[0] = 0`.
- Step 3: Relax edges in topological order:
  - For each outgoing edge `(v, wt)`: `dist[v] = Math.min(dist[v], dist[u] + wt)`.

TIME COMPLEXITY: O(V + E) linear time.
SPACE COMPLEXITY: O(V + E).
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

class Solution {
    private static class Edge {
        int to, wt;
        Edge(int to, int wt) { this.to = to; this.wt = wt; }
    }

    private void topoDFS(int node, boolean[] visited, Stack<Integer> stack, List<List<Edge>> adj) {
        visited[node] = true;
        for (Edge e : adj.get(node)) {
            if (!visited[e.to]) {
                topoDFS(e.to, visited, stack, adj);
            }
        }
        stack.push(node);
    }

    public int[] shortestPath(int N, int M, int[][] edges) {
        List<List<Edge>> adj = new ArrayList<>();
        for (int i = 0; i < N; i++) adj.add(new ArrayList<>());

        for (int[] edge : edges) {
            adj.get(edge[0]).add(new Edge(edge[1], edge[2]));
        }

        boolean[] visited = new boolean[N];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            if (!visited[i]) topoDFS(i, visited, stack, adj);
        }

        int[] dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (dist[node] != Integer.MAX_VALUE) {
                for (Edge e : adj.get(node)) {
                    if (dist[node] + e.wt < dist[e.to]) {
                        dist[e.to] = dist[node] + e.wt;
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            if (dist[i] == Integer.MAX_VALUE) dist[i] = -1;
        }
        return dist;
    }
}
