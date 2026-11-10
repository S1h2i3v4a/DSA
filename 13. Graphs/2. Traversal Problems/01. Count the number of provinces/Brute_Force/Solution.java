/*
QUESTION:
There are `n` cities. Some of them are connected, while some are not.
A province is a group of directly or indirectly connected cities.
Return the total number of provinces using DFS traversal.

APPROACH:
- Use DFS with a boolean `visited` array.
- For each unvisited city `i` from 0 to `n - 1`:
  - Run DFS to mark all cities connected to `i`.
  - Increment provinces count.

TIME COMPLEXITY: O(N^2) inspecting adjacency matrix.
SPACE COMPLEXITY: O(N) visited array and recursion stack.
*/

class Solution {
    private void dfs(int city, int[][] isConnected, boolean[] visited) {
        visited[city] = true;
        for (int j = 0; j < isConnected.length; j++) {
            if (isConnected[city][j] == 1 && !visited[j]) {
                dfs(j, isConnected, visited);
            }
        }
    }

    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int provinces = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                provinces++;
                dfs(i, isConnected, visited);
            }
        }
        return provinces;
    }
}
