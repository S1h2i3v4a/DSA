/**
 * Problem Name: Detect Cycle in an Undirected Graph
 * Platform: GeeksforGeeks
 * Difficulty: Medium
 * 
 * Time Complexity: O(V + 2E) where V is vertices and E is edges
 * Space Complexity: O(V) for visited array and queue/stack
 * 
 * Approach:
 * BFS & DFS with Parent Tracking:
 * In an undirected graph, an edge connects two nodes bidirectionally.
 * When exploring neighbors of node 'u':
 * - If neighbor 'v' is already visited and 'v != parent', a cycle exists!
 * - If neighbor 'v' is unvisited, recurse/push with 'u' as the parent.
 * Handle disconnected graphs by looping through all components from 0 to V-1.
 */

import java.util.*;

class Detect_Cycle_In_Undirected_Graph {

    // Approach 1: BFS with Parent Tracking
    public boolean isCycleBFS(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (checkCycleBFS(i, adj, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static class NodeParent {
        int node;
        int parent;

        NodeParent(int node, int parent) {
            this.node = node;
            this.parent = parent;
        }
    }

    private boolean checkCycleBFS(int start, ArrayList<ArrayList<Integer>> adj, boolean[] visited) {
        Queue<NodeParent> queue = new LinkedList<>();
        queue.offer(new NodeParent(start, -1));
        visited[start] = true;

        while (!queue.isEmpty()) {
            NodeParent curr = queue.poll();
            int u = curr.node;
            int parent = curr.parent;

            for (int v : adj.get(u)) {
                if (!visited[v]) {
                    visited[v] = true;
                    queue.offer(new NodeParent(v, u));
                } else if (v != parent) {
                    return true; // Visited and not parent => Cycle!
                }
            }
        }
        return false;
    }

    // Approach 2: DFS with Parent Tracking
    public boolean isCycleDFS(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (checkCycleDFS(i, -1, adj, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkCycleDFS(int u, int parent, ArrayList<ArrayList<Integer>> adj, boolean[] visited) {
        visited[u] = true;

        for (int v : adj.get(u)) {
            if (!visited[v]) {
                if (checkCycleDFS(v, u, adj, visited)) {
                    return true;
                }
            } else if (v != parent) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Detect_Cycle_In_Undirected_Graph solver = new Detect_Cycle_In_Undirected_Graph();
        int V = 5;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());

        // Graph with cycle: 0-1, 1-2, 2-3, 3-0, 2-4
        adj.get(0).add(1); adj.get(1).add(0);
        adj.get(1).add(2); adj.get(2).add(1);
        adj.get(2).add(3); adj.get(3).add(2);
        adj.get(3).add(0); adj.get(0).add(3);
        adj.get(2).add(4); adj.get(4).add(2);

        System.out.println("Cycle detected (BFS): " + solver.isCycleBFS(V, adj)); // Expected: true
        System.out.println("Cycle detected (DFS): " + solver.isCycleDFS(V, adj)); // Expected: true
    }
}
