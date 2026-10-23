/*
Question:
Given an n x n matrix 'isConnected' representing connections between cities (nodes), find the total number of provinces in the graph.

Explanation:
- The problem can be solved using Depth First Search (DFS).
- We first convert the given matrix 'isConnected' into an adjacency list 'adj' representing the graph.
- We use a 'vis' vector to keep track of visited cities, initialized to 'false' for all cities.
- We initialize a variable 'ans' to store the number of provinces.
- We then perform DFS from each city and mark all directly or indirectly connected cities as visited.
- We increment the 'ans' for each unvisited city and continue the process until all cities are visited.
- Finally, we return the 'ans' which represents the total number of provinces in the graph.

Time Complexity:
- The time complexity is O(n^2), where n is the number of cities. We traverse the entire 'isConnected' matrix to construct the adjacency list.

Space Complexity:
- The space complexity is O(n), where n is the number of cities. We use extra space for the adjacency list and the 'vis' vector.

*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public int findCircleNum(int[][] isConnected) {
        int V = isConnected.length;
        boolean[] vis = new boolean[V];
        int count = 0;
        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                count++;
                dfs(i, isConnected, vis);
            }
        }
        return count;
    }
    private void dfs(int node, int[][] isConnected, boolean[] vis) {
        vis[node] = true;
        for (int j = 0; j < isConnected.length; j++) {
            if (isConnected[node][j] == 1 && !vis[j]) {
                dfs(j, isConnected, vis);
            }
        }
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
