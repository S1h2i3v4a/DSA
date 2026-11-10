/*
QUESTION:
There are `n` cities connected by some number of flights.
Return the cheapest price from `src` to `dst` with at most `k` stops. If no such route exists, return -1.

APPROACH:
- BFS queue storing `(stops, node, cost)`.
- We order by `stops` (queue increases stops level-by-level).
- For each edge `(neighbor, price)`:
  - If `cost + price < dist[neighbor]` and `stops <= k`:
    - Update `dist[neighbor] = cost + price`.
    - Offer `(stops + 1, neighbor, cost + price)`.

TIME COMPLEXITY: O(K * E).
SPACE COMPLEXITY: O(V + E) for adjacency list and distance array.
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    private static class Flight {
        int to, price;
        Flight(int to, int price) { this.to = to; this.price = price; }
    }

    private static class Tuple {
        int stops, node, cost;
        Tuple(int stops, int node, int cost) {
            this.stops = stops;
            this.node = node;
            this.cost = cost;
        }
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        List<List<Flight>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] f : flights) {
            adj.get(f[0]).add(new Flight(f[1], f[2]));
        }

        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        Queue<Tuple> queue = new LinkedList<>();
        queue.offer(new Tuple(0, src, 0));

        while (!queue.isEmpty()) {
            Tuple curr = queue.poll();
            int stops = curr.stops;
            int u = curr.node;
            int cost = curr.cost;

            if (stops > k) continue;

            for (Flight f : adj.get(u)) {
                int v = f.to;
                int price = f.price;

                if (cost + price < dist[v] && stops <= k) {
                    dist[v] = cost + price;
                    queue.offer(new Tuple(stops + 1, v, cost + price));
                }
            }
        }

        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
    }
}
