/*QUESTION:
Given an undirected graph and an integer M. The task is to determine if the graph can be colored with at most M colors such that no two adjacent vertices of the graph are colored with the same color. Here coloring of a graph means the assignment of colors to all vertices. Print 1 if it is possible to colour vertices and 0 otherwise.

Approach:
We can solve this problem using backtracking.
1. Create a helper function called 'isPossible' that takes the graph, an array of colors assigned to vertices, the number of vertices, the color to be assigned, and the current vertex.
   - Iterate through all the adjacent vertices of the current vertex.
   - If an adjacent vertex is already colored with the same color, return false.
   - If there is no conflict, continue checking other adjacent vertices.
   - If all adjacent vertices have different colors or are uncolored, return true.
2. Create another helper function called 'solve' that takes the graph, the number of colors, the number of vertices, an array of colors assigned to vertices, and the current vertex.
   - If all vertices have been assigned colors, return true.
   - Iterate through all possible colors from 1 to M.
   - If it is possible to assign the current color to the current vertex without any conflict, assign the color and recursively call the 'solve' function for the next vertex.
   - If the 'solve' function returns true, return true.
   - If the 'solve' function returns false, try the next color.
   - If no color can be assigned to the current vertex without any conflict, return false.
3. Create the main function called 'graphColoring' that initializes an array of colors and calls the 'solve' function to check if it is possible to color the graph.
   - If the 'solve' function returns true, return true.
   - Otherwise, return false.

Time Complexity: O(M^N), where M is the number of colors and N is the number of vertices in the graph. In the worst case, we have to try all possible color combinations for all vertices.
Space Complexity: O(N), as we are using an array of colors to store the assigned colors for each vertex.

CODE:*/

import java.util.*;
import java.io.*;

// CODE:-
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
