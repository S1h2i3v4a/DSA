/*
QUESTION:
Given a weighted, undirected and connected graph of V vertices and E edges, find the sum of weights of the edges of the Minimum Spanning Tree using Prim's Algorithm.

APPROACH:
- PriorityQueue (Min-Heap) storing pairs `(weight, node)`.
- Maintain a `visited` array.
- Start at node 0 with weight 0.
- While heap is non-empty:
  - Pop edge with smallest weight.
  - If already visited, continue.
  - Mark visited, add weight to MST total.
  - Push all unvisited adjacent edges into heap.

TIME COMPLEXITY: O(E log V).
SPACE COMPLEXITY: O(V + E).
*/

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    private static class NodePair implements Comparable<NodePair> {
        int weight, node;
        NodePair(int weight, int node) {
            this.weight = weight;
            this.node = node;
        }
        public int compareTo(NodePair other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    static int spanningTree(int V, int E, List<List<int[]>> adj) {
        boolean[] visited = new boolean[V];
        PriorityQueue<NodePair> pq = new PriorityQueue<>();
        pq.offer(new NodePair(0, 0));
        int mstSum = 0;

        while (!pq.isEmpty()) {
            NodePair curr = pq.poll();
            int u = curr.node;
            int wt = curr.weight;

            if (visited[u]) continue;
            visited[u] = true;
            mstSum += wt;

            for (int[] edge : adj.get(u)) {
                int v = edge[0];
                int weight = edge[1];
                if (!visited[v]) {
                    pq.offer(new NodePair(weight, v));
                }
            }
        }
        return mstSum;
    }
}
