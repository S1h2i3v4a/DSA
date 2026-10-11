/**
 * Problem Name: Number of Ways to Arrive at Destination
 * Platform: LeetCode (LC 1976)
 * Difficulty: Medium
 * 
 * Time Complexity: O(E * log V) where V is intersections and E is roads
 * Space Complexity: O(V + E) for adjacency list, distance array, and ways array
 * 
 * Approach:
 * Dijkstra's Algorithm with Path Counting:
 * 1. Build adjacency list for bi-directional graph: u -> (v, weight).
 * 2. Maintain:
 *    - dist[n] initialized with Long.MAX_VALUE, dist[0] = 0 (using long to avoid overflow).
 *    - ways[n] initialized with 0, ways[0] = 1 (number of shortest paths to reach node).
 * 3. PriorityQueue (Min-Heap) storing Pair(node, time) ordered by time ascending.
 * 4. While PQ is not empty:
 *    - Poll (u, time). If time > dist[u], continue.
 *    - For each neighbor (v, weight):
 *      - If time + weight < dist[v]:
 *        Found a strictly shorter path to v:
 *        dist[v] = time + weight;
 *        ways[v] = ways[u];
 *        Offer (v, dist[v]) to PQ.
 *      - Else if time + weight == dist[v]:
 *        Found another path to v with identical minimum distance:
 *        ways[v] = (ways[v] + ways[u]) % MOD;
 * 5. Return (int) ways[n - 1].
 */

import java.util.*;

class Number_Of_Ways_To_Arrive_At_Destination {
    private static class Edge {
        int to;
        long weight;

        Edge(int to, long weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    private static class NodePair implements Comparable<NodePair> {
        int node;
        long time;

        NodePair(int node, long time) {
            this.node = node;
            this.time = time;
        }

        @Override
        public int compareTo(NodePair other) {
            return Long.compare(this.time, other.time);
        }
    }

    public int countPaths(int n, int[][] roads) {
        long MOD = 1_000_000_007L;

        List<List<Edge>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] r : roads) {
            int u = r[0];
            int v = r[1];
            long w = r[2];
            adj.get(u).add(new Edge(v, w));
            adj.get(v).add(new Edge(u, w));
        }

        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;

        long[] ways = new long[n];
        ways[0] = 1;

        PriorityQueue<NodePair> pq = new PriorityQueue<>();
        pq.offer(new NodePair(0, 0));

        while (!pq.isEmpty()) {
            NodePair curr = pq.poll();
            int u = curr.node;
            long time = curr.time;

            if (time > dist[u]) continue;

            for (Edge edge : adj.get(u)) {
                int v = edge.to;
                long w = edge.weight;

                if (time + w < dist[v]) {
                    dist[v] = time + w;
                    ways[v] = ways[u];
                    pq.offer(new NodePair(v, dist[v]));
                } else if (time + w == dist[v]) {
                    ways[v] = (ways[v] + ways[u]) % MOD;
                }
            }
        }

        return (int) ways[n - 1];
    }

    public static void main(String[] args) {
        Number_Of_Ways_To_Arrive_At_Destination solver = new Number_Of_Ways_To_Arrive_At_Destination();
        int n = 7;
        int[][] roads = {
            {0, 6, 7}, {0, 1, 2}, {1, 2, 3}, {1, 3, 3},
            {6, 3, 3}, {3, 5, 1}, {6, 5, 1}, {2, 5, 1},
            {0, 4, 5}, {4, 6, 2}
        };

        System.out.println("Number of ways to reach destination: " + solver.countPaths(n, roads));
        // Expected: 4
    }
}
