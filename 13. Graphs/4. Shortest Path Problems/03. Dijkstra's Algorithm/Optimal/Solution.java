/*
QUESTION:
Given a weighted, undirected and connected graph of V vertices and an adjacency list,
find the shortest distance of all the vertex's from the source vertex S using Dijkstra's Algorithm.

APPROACH:
- PriorityQueue (Min-Heap) storing pairs `(distance, node)`.
- Initialize `dist` array with infinity, `dist[S] = 0`.
- Push `(0, S)` into heap.
- While heap is non-empty:
  - Pop min distance node `u`.
  - For each neighbor `(v, wt)`:
    - If `dist[u] + wt < dist[v]`, update `dist[v] = dist[u] + wt`, offer `(dist[v], v)`.

TIME COMPLEXITY: O(E log V) with PriorityQueue.
SPACE COMPLEXITY: O(V) for distance array and heap.
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    private static class NodePair implements Comparable<NodePair> {
        int dist, node;
        NodePair(int dist, int node) {
            this.dist = dist;
            this.node = node;
        }
        public int compareTo(NodePair other) {
            return Integer.compare(this.dist, other.dist);
        }
    }

    public static int[] dijkstra(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[S] = 0;

        PriorityQueue<NodePair> pq = new PriorityQueue<>();
        pq.offer(new NodePair(0, S));

        while (!pq.isEmpty()) {
            NodePair curr = pq.poll();
            int u = curr.node;
            int d = curr.dist;

            if (d > dist[u]) continue;

            for (ArrayList<Integer> edge : adj.get(u)) {
                int v = edge.get(0);
                int wt = edge.get(1);

                if (dist[u] + wt < dist[v]) {
                    dist[v] = dist[u] + wt;
                    pq.offer(new NodePair(dist[v], v));
                }
            }
        }
        return dist;
    }
}
