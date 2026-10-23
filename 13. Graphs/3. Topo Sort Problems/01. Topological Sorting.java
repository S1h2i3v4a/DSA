/*
QUESTION:
Given a DAG (directed acyclic graph), print the Topological sorting of a given graph.

APPROACH:
- Topological sorting is a linear ordering of vertices in a directed acyclic graph (DAG) such that for every directed edge u -> v, vertex u comes before v in the ordering.
- To find the topological sorting, we can use Depth-First Search (DFS) with backtracking.
- We perform DFS starting from each unvisited node, and as we finish exploring a node and backtrack, we add it to the front of the topological sorting order.

COMPLEXITY ANALYSIS:
- Time Complexity: O(V + E), where V is the number of vertices (nodes) and E is the number of edges in the graph. We visit each node and each edge exactly once during the DFS.
- Space Complexity: O(V), where V is the number of vertices (nodes) in the graph. We use additional space to store the visited status of the nodes and the topological sorting order.
*/

import java.util.*;
import java.io.*;

// CODE:-
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
