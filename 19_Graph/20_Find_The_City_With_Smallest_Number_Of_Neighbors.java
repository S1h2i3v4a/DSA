/**
 * Problem Name: Find the City With the Smallest Number of Neighbors at a Threshold Distance
 * Platform: LeetCode (LC 1334)
 * Difficulty: Medium
 * 
 * Time Complexity: O(N^3) using Floyd-Warshall Algorithm
 * Space Complexity: O(N^2) for distance matrix
 * 
 * Approach:
 * Floyd-Warshall All-Pairs Shortest Path:
 * 1. Initialize dist[n][n] matrix with infinity, and dist[i][i] = 0.
 * 2. Populate direct edge weights from edges array.
 * 3. Run Floyd-Warshall:
 *    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]).
 * 4. For each city i:
 *    Count how many other cities j have dist[i][j] <= distanceThreshold.
 * 5. Track the minimum reachable count, and if tied, pick the city with the largest index (city >= bestCity).
 */

import java.util.*;

class Find_The_City_With_Smallest_Number_Of_Neighbors {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int INF = 100_000_000;
        int[][] dist = new int[n][n];

        for (int[] row : dist) {
            Arrays.fill(row, INF);
        }
        for (int i = 0; i < n; i++) {
            dist[i][i] = 0;
        }

        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            int w = e[2];
            dist[u][v] = w;
            dist[v][u] = w;
        }

        // Floyd-Warshall
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        int minReachable = n;
        int bestCity = -1;

        for (int i = 0; i < n; i++) {
            int reachable = 0;
            for (int j = 0; j < n; j++) {
                if (i != j && dist[i][j] <= distanceThreshold) {
                    reachable++;
                }
            }

            // Pick city with smallest reachable count, preferring greatest index on tie
            if (reachable <= minReachable) {
                minReachable = reachable;
                bestCity = i;
            }
        }

        return bestCity;
    }

    public static void main(String[] args) {
        Find_The_City_With_Smallest_Number_Of_Neighbors solver = new Find_The_City_With_Smallest_Number_Of_Neighbors();
        int n = 4;
        int[][] edges = {
            {0, 1, 3},
            {1, 2, 1},
            {1, 3, 4},
            {2, 3, 1}
        };
        int distanceThreshold = 4;

        System.out.println("Best city: " + solver.findTheCity(n, edges, distanceThreshold)); // Expected: 3
    }
}
