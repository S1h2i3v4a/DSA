/**
 * Problem Name: M-Coloring Problem
 * Platform: GeeksforGeeks
 * Difficulty: Medium
 * 
 * Time Complexity: O(M^V) where V is vertices count and M is available colors.
 * Space Complexity: O(V) for color assignment array and recursion depth stack.
 * 
 * Approach:
 * Graph Backtracking.
 * Try assigning colors from 1 to M to vertex 0. For each valid color (no adjacent vertex has the same color),
 * recurse to vertex 1. If all vertices 0 to V-1 are colored, return true. If no color works, backtrack.
 */

import java.util.Arrays;
import java.util.List;

class M_Coloring_Problem {

    public static boolean graphColoring(int v, List<int[]> edges, int m) {
        // Build adjacency matrix/list
        boolean[][] graph = new boolean[v][v];
        for (int[] edge : edges) {
            graph[edge[0]][edge[1]] = true;
            graph[edge[1]][edge[0]] = true;
        }

        int[] color = new int[v];
        return solve(0, graph, color, v, m);
    }

    private static boolean solve(int node, boolean[][] graph, int[] color, int v, int m) {
        if (node == v) {
            return true;
        }

        for (int c = 1; c <= m; c++) {
            if (isSafe(node, graph, color, c, v)) {
                color[node] = c;
                if (solve(node + 1, graph, color, v, m)) {
                    return true;
                }
                color[node] = 0; // Backtrack
            }
        }

        return false;
    }

    private static boolean isSafe(int node, boolean[][] graph, int[] color, int c, int v) {
        for (int i = 0; i < v; i++) {
            if (graph[node][i] && color[i] == c) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int v = 4;
        int m = 3;
        List<int[]> edges = Arrays.asList(
            new int[]{0, 1},
            new int[]{1, 2},
            new int[]{2, 3},
            new int[]{3, 0},
            new int[]{0, 2}
        );

        System.out.println("Is graph colorable with " + m + " colors? " + graphColoring(v, edges, m));
    }
}
