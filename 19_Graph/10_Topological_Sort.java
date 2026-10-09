/**
 * Problem Name: Topological Sort (DFS & Kahn's BFS Algorithm)
 * Platform: GeeksforGeeks
 * Difficulty: Medium
 * 
 * Time Complexity: O(V + E) where V is vertices and E is edges
 * Space Complexity: O(V) for visited array / in-degree array and recursion stack / queue
 * 
 * Approach:
 * Linear ordering of vertices such that for every directed edge u -> v, u appears before v.
 * Applicable only on Directed Acyclic Graphs (DAG).
 * 
 * Approach 1 (DFS using Stack):
 * - For each unvisited node, run DFS.
 * - After exploring all neighbors of u, push u onto a Stack.
 * - Pop all elements from stack to get topological order.
 * 
 * Approach 2 (Kahn's BFS Algorithm):
 * - Calculate in-degree for all vertices.
 * - Enqueue vertices with in-degree == 0.
 * - While queue is not empty: pop node, add to result, decrement in-degree of neighbors.
 *   If neighbor in-degree becomes 0, enqueue it.
 */

import java.util.*;

class Topological_Sort {

    // Approach 1: DFS with Stack
    public int[] topoSortDFS(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs(i, adj, visited, stack);
            }
        }

        int[] result = new int[V];
        int idx = 0;
        while (!stack.isEmpty()) {
            result[idx++] = stack.pop();
        }
        return result;
    }

    private void dfs(int u, ArrayList<ArrayList<Integer>> adj, boolean[] visited, Stack<Integer> stack) {
        visited[u] = true;

        for (int v : adj.get(u)) {
            if (!visited[v]) {
                dfs(v, adj, visited, stack);
            }
        }

        stack.push(u);
    }

    // Approach 2: Kahn's BFS Algorithm
    public int[] topoSortBFS(int V, ArrayList<ArrayList<Integer>> adj) {
        int[] inDegree = new int[V];
        for (int u = 0; u < V; u++) {
            for (int v : adj.get(u)) {
                inDegree[v]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        int[] result = new int[V];
        int idx = 0;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            result[idx++] = u;

            for (int v : adj.get(u)) {
                inDegree[v]--;
                if (inDegree[v] == 0) {
                    queue.offer(v);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Topological_Sort solver = new Topological_Sort();
        int V = 6;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());

        // Edges: 5->0, 5->2, 4->0, 4->1, 2->3, 3->1
        adj.get(5).add(0);
        adj.get(5).add(2);
        adj.get(4).add(0);
        adj.get(4).add(1);
        adj.get(2).add(3);
        adj.get(3).add(1);

        System.out.println("Topological Sort (DFS): " + Arrays.toString(solver.topoSortDFS(V, adj)));
        System.out.println("Topological Sort (BFS): " + Arrays.toString(solver.topoSortBFS(V, adj)));
    }
}
