/**
 * Problem Name: Cheapest Flights Within K Stops
 * Platform: LeetCode (LC 787)
 * Difficulty: Medium
 * 
 * Time Complexity: O(K * E) where E is number of flights
 * Space Complexity: O(V + E) for adjacency list, cost array, and BFS queue
 * 
 * Approach:
 * Level-by-Level BFS (Dijkstra Variant by Stops):
 * Since stops increment uniformly by +1 at each step, a simple Queue sorted by stops (BFS level)
 * guarantees we process paths with fewer stops first.
 * 1. Build adjacency list: flight u -> (v, price).
 * 2. Maintain dist[] array storing minimum cost to reach each city. Initialize with infinity, dist[src] = 0.
 * 3. Enqueue Tuple(stops = 0, city = src, cost = 0).
 * 4. While Queue is not empty:
 *    - Poll tuple. If stops > k, continue (cannot expand further).
 *    - For each flight u -> v with price:
 *      If cost + price < dist[v]:
 *        dist[v] = cost + price;
 *        Enqueue (stops + 1, v, cost + price).
 * 5. Return dist[dst] == infinity ? -1 : dist[dst].
 */

import java.util.*;

class Cheapest_Flights_Within_K_Stops {
    private static class Edge {
        int to;
        int price;

        Edge(int to, int price) {
            this.to = to;
            this.price = price;
        }
    }

    private static class NodeInfo {
        int stops;
        int city;
        int cost;

        NodeInfo(int stops, int city, int cost) {
            this.stops = stops;
            this.city = city;
            this.cost = cost;
        }
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        List<List<Edge>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] f : flights) {
            adj.get(f[0]).add(new Edge(f[1], f[2]));
        }

        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        Queue<NodeInfo> queue = new LinkedList<>();
        queue.offer(new NodeInfo(0, src, 0));

        while (!queue.isEmpty()) {
            NodeInfo curr = queue.poll();
            int stops = curr.stops;
            int u = curr.city;
            int cost = curr.cost;

            if (stops > k) continue;

            for (Edge edge : adj.get(u)) {
                int v = edge.to;
                int price = edge.price;

                if (cost + price < dist[v]) {
                    dist[v] = cost + price;
                    queue.offer(new NodeInfo(stops + 1, v, cost + price));
                }
            }
        }

        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
    }

    public static void main(String[] args) {
        Cheapest_Flights_Within_K_Stops solver = new Cheapest_Flights_Within_K_Stops();
        int n = 4;
        int[][] flights = {
            {0, 1, 100},
            {1, 2, 100},
            {2, 0, 100},
            {1, 3, 600},
            {2, 3, 200}
        };
        int src = 0, dst = 3, k = 1;

        System.out.println("Cheapest price with at most " + k + " stops: " + solver.findCheapestPrice(n, flights, src, dst, k));
        // Expected: 700 (0 -> 1 -> 3 with 1 stop)
    }
}
