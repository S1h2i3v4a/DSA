/*
QUESTION:-
You are in a city that consists of n intersections numbered from 0 to n - 1 with bi-directional roads between some intersections. The inputs are generated such that you can reach any intersection from any other intersection, and that there is at most one road between any two intersections.

You are given an integer n and a 2D integer array roads where roads[i] = [ui, vi, timei] means that there is a road between intersections ui and vi that takes timei minutes to travel. You want to know in how many ways you can travel from intersection 0 to intersection n - 1 in the shortest amount of time.

Return the number of ways you can arrive at your destination in the shortest amount of time. Since the answer may be large, return it modulo 10^9 + 7.

Approach:
1. We can use Dijkstra's algorithm to find the shortest path from intersection 0 to intersection n-1 and also keep track of the number of ways to reach each intersection in the shortest time.
2. We start with intersection 0 and keep track of the minimum time required to reach each intersection.
3. During the process, we also keep track of the number of ways to reach each intersection in the shortest time.
4. If we find a shorter path to a particular intersection, we update its minimum time and reset the number of ways to reach that intersection to the number of ways to reach the previous intersection.
5. If we find an equal time path to a particular intersection, we add the number of ways to reach the previous intersection to the number of ways to reach the current intersection.

Complexity Analysis:
- Let n be the number of intersections and E be the number of roads in the input.
- Dijkstra's algorithm has a time complexity of O((n+E)log(n)) using a priority queue.
- The space complexity is O(n) to store the distance and ways arrays.
*/

import java.util.*;
import java.io.*;

// CODE:-
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
