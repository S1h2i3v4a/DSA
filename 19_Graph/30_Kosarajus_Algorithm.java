/**
 * Problem Name: Strongly Connected Components (Kosaraju's Algorithm)
 * Platform: GeeksforGeeks / Standard Data Structure & Algorithms
 * Difficulty: Hard
 *
 * Time Complexity: O(V + E) for 2 DFS traversals and graph reversal
 * Space Complexity: O(V + E) for reversed graph adjacency list, visited array, and call stack
 *
 * Approach:
 *   - A Strongly Connected Component (SCC) is a maximal subgraph where every vertex
 *     is reachable from every other vertex.
 *   - Kosaraju's Algorithm finds all SCCs in a directed graph in 3 steps:
 *     1. Step 1 (Finish Time Ordering):
 *        Run DFS on the original graph. When a node finishes exploring all its neighbors,
 *        push it onto a Stack. The stack top will have the node with the latest finishing time.
 *     2. Step 2 (Graph Transposition):
 *        Reverse the directions of all edges in the graph (create adjT).
 *     3. Step 3 (DFS on Transposed Graph):
 *        Pop nodes from the stack one by one. If a popped node is unvisited, it forms
 *        the start of a new SCC. Perform DFS on the transposed graph to mark all nodes
 *        in this SCC.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class KosarajusAlgorithm {

    // Returns the number of strongly connected components
    public static int kosaraju(int V, ArrayList<ArrayList<Integer>> adj) {
        // Step 1: Sort vertices by finishing times
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfsOrder(i, adj, visited, stack);
            }
        }

        // Step 2: Create transposed (reversed) graph
        ArrayList<ArrayList<Integer>> adjT = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adjT.add(new ArrayList<>());
        }

        for (int u = 0; u < V; u++) {
            for (int v : adj.get(u)) {
                adjT.get(v).add(u); // Reverse edge u -> v to v -> u
            }
        }

        // Step 3: DFS according to stack finishing times on transposed graph
        for (int i = 0; i < V; i++) {
            visited[i] = false;
        }

        int sccCount = 0;
        List<List<Integer>> sccs = new ArrayList<>();

        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited[node]) {
                sccCount++;
                List<Integer> currentSCC = new ArrayList<>();
                dfsCollect(node, adjT, visited, currentSCC);
                sccs.add(currentSCC);
            }
        }

        return sccCount;
    }

    // DFS for Step 1
    private static void dfsOrder(int node, ArrayList<ArrayList<Integer>> adj,
                                 boolean[] visited, Stack<Integer> stack) {
        visited[node] = true;
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfsOrder(neighbor, adj, visited, stack);
            }
        }
        stack.push(node);
    }

    // DFS for Step 3
    private static void dfsCollect(int node, ArrayList<ArrayList<Integer>> adjT,
                                   boolean[] visited, List<Integer> currentSCC) {
        visited[node] = true;
        currentSCC.add(node);
        for (int neighbor : adjT.get(node)) {
            if (!visited[neighbor]) {
                dfsCollect(neighbor, adjT, visited, currentSCC);
            }
        }
    }

    public static void main(String[] args) {
        // Test Case 1:
        // V = 5, edges: 0->2, 2->1, 1->0, 0->3, 3->4
        // SCC 1: {0, 1, 2}, SCC 2: {3}, SCC 3: {4} -> Total = 3
        int V1 = 5;
        ArrayList<ArrayList<Integer>> adj1 = new ArrayList<>();
        for (int i = 0; i < V1; i++) adj1.add(new ArrayList<>());

        adj1.get(0).add(2);
        adj1.get(2).add(1);
        adj1.get(1).add(0);
        adj1.get(0).add(3);
        adj1.get(3).add(4);

        System.out.println("Test 1 SCC Count: " + kosaraju(V1, adj1)); // 3

        // Test Case 2:
        // V = 4, edges: 0->1, 1->2, 2->3 (DAG, each node is an SCC)
        // Total = 4
        int V2 = 4;
        ArrayList<ArrayList<Integer>> adj2 = new ArrayList<>();
        for (int i = 0; i < V2; i++) adj2.add(new ArrayList<>());

        adj2.get(0).add(1);
        adj2.get(1).add(2);
        adj2.get(2).add(3);

        System.out.println("Test 2 SCC Count: " + kosaraju(V2, adj2)); // 4
    }
}
