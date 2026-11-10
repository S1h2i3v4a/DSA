/*
QUESTION:
Given a Directed Acyclic Graph (DAG) with V vertices and adj list, find a Topological Sorting of that graph using DFS.

APPROACH:
- DFS traversal tracking `visited` boolean array.
- For each unvisited node, visit all its children recursively.
- After all children are visited, push the current node onto a Stack.
- Pop all elements from stack to get topological order.

TIME COMPLEXITY: O(V + E).
SPACE COMPLEXITY: O(V) for visited array and stack.
*/

import java.util.ArrayList;
import java.util.Stack;

class Solution {
    private void dfs(int node, boolean[] visited, Stack<Integer> stack, ArrayList<ArrayList<Integer>> adj) {
        visited[node] = true;
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, visited, stack, adj);
            }
        }
        stack.push(node);
    }

    public int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs(i, visited, stack, adj);
            }
        }

        int[] result = new int[V];
        int idx = 0;
        while (!stack.isEmpty()) {
            result[idx++] = stack.pop();
        }
        return result;
    }
}
