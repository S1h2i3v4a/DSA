/*
QUESTION:
You are given a network of `n` nodes labeled from 1 to `n`. You are also given `times`, a list of travel times as directed edges `times[i] = (ui, vi, wi)`.
Return the minimum time it takes for all the `n` nodes to receive the signal starting from node `k`. If impossible, return -1.

APPROACH:
- Dijkstra's Algorithm from source `k`.
- Maintain `dist` array.
- Signal reaches all nodes in `max(dist[1...n])`.
- If any node remains unreachable (infinity), return -1.

TIME COMPLEXITY: O(E log V).
SPACE COMPLEXITY: O(V + E).
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    private static class Pair implements Comparable<Pair> {
        int node, time;
        Pair(int node, int time) { this.node = node; this.time = time; }
        public int compareTo(Pair other) { return Integer.compare(this.time, other.time); }
    }

    public int networkDelayTime(int[][] times, int n, int k) {
        List<List<Pair>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());
        for (int[] t : times) {
            adj.get(t[0]).add(new Pair(t[1], t[2]));
        }

        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.offer(new Pair(k, 0));

        while (!pq.isEmpty()) {
            Pair curr = pq.poll();
            int u = curr.node;
            int t = curr.time;

            if (t > dist[u]) continue;

            for (Pair next : adj.get(u)) {
                if (dist[u] + next.time < dist[next.node]) {
                    dist[next.node] = dist[u] + next.time;
                    pq.offer(new Pair(next.node, dist[next.node]));
                }
            }
        }

        int maxTime = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == Integer.MAX_VALUE) return -1;
            maxTime = Math.max(maxTime, dist[i]);
        }
        return maxTime;
    }
}
