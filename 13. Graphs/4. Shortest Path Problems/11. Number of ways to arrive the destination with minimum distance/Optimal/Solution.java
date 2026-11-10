/*
QUESTION:
You are in a city that consists of `n` intersections numbered from 0 to `n - 1` with bi-directional roads.
Find the number of ways you can arrive at your destination `n - 1` in the shortest amount of time.
Return answer modulo 10^9 + 7.

APPROACH:
- Modified Dijkstra maintaining:
  - `dist[i]`: minimum time to reach node `i`.
  - `ways[i]`: number of shortest paths to reach node `i`.
- Initialize `dist[0] = 0`, `ways[0] = 1`.
- When relaxing edge `(u, v, wt)`:
  - If `dist[u] + wt < dist[v]`: strictly shorter path found. Update `dist[v] = dist[u] + wt`, `ways[v] = ways[u]`, enqueue `(dist[v], v)`.
  - If `dist[u] + wt == dist[v]`: another shortest path found. Update `ways[v] = (ways[v] + ways[u]) % MOD`.

TIME COMPLEXITY: O(E log V).
SPACE COMPLEXITY: O(V + E).
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    private static class Pair implements Comparable<Pair> {
        long dist;
        int node;
        Pair(long dist, int node) { this.dist = dist; this.node = node; }
        public int compareTo(Pair other) { return Long.compare(this.dist, other.dist); }
    }

    public int countPaths(int n, int[][] roads) {
        long MOD = 1000000007L;
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] r : roads) {
            adj.get(r[0]).add(new int[]{r[1], r[2]});
            adj.get(r[1]).add(new int[]{r[0], r[2]});
        }

        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        long[] ways = new long[n];

        dist[0] = 0;
        ways[0] = 1;

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.offer(new Pair(0, 0));

        while (!pq.isEmpty()) {
            Pair curr = pq.poll();
            long d = curr.dist;
            int u = curr.node;

            if (d > dist[u]) continue;

            for (int[] edge : adj.get(u)) {
                int v = edge[0];
                long time = edge[1];

                if (d + time < dist[v]) {
                    dist[v] = d + time;
                    ways[v] = ways[u];
                    pq.offer(new Pair(dist[v], v));
                } else if (d + time == dist[v]) {
                    ways[v] = (ways[v] + ways[u]) % MOD;
                }
            }
        }
        return (int) ways[n - 1];
    }
}
