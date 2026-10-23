/* QUESTION:

There are n cities connected by some number of flights. You are given an array flights where flights[i] = [fromi, toi, pricei] indicates that there is a flight from city fromi to city toi with cost pricei.

You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops. If there is no such route, return -1.

Example:

Input: n = 4, flights = [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]], src = 0, dst = 3, k = 1
Output: 700
Explanation:
The graph is shown above.
The optimal path with at most 1 stop from city 0 to 3 is marked in red and has cost 100 + 600 = 700.
Note that the path through cities [0,1,2,3] is cheaper but is invalid because it uses 2 stops.

APPROACH:
- We can use Breadth-First Search (BFS) to find the cheapest price from the source to the destination with at most k stops.
- We will create an adjacency list to represent the graph, where each node will have a list of neighboring nodes with their corresponding prices.
- We will use a queue to perform BFS starting from the source node.
- During BFS, we will keep track of the distance to each node and the number of stops made so far.
- If we reach the destination node and the number of stops is less than or equal to k, we will update the minimum cost to reach the destination.
- Continue BFS until we have explored all possible paths with at most k stops.
- Return the minimum cost to reach the destination if it is reachable, otherwise return -1.

COMPLEXITY ANALYSIS:
- The time complexity is O(n*k) since we can have at most k stops and for each stop, we will explore all n nodes.
- The space complexity is O(n) to store the distance values.

CODE:
*/

import java.util.*;
import java.io.*;

// CODE:-
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
