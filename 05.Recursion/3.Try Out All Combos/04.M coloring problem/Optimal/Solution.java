/*
QUESTION:-
Given an undirected graph and an integer M. The task is to determine if the graph can be colored with at most M colors such that no two adjacent vertices of the graph are colored with the same color.

Example:
Input: N = 4, M = 3, E = 5, Edges = [(0,1),(1,2),(2,3),(3,0),(0,2)]
Output: true
*/

/*
APPROACH:-
Backtracking Node by Node: Assign colors 1 to m to each vertex node. For each color, check if any adjacent node already has that color. If valid, recursively color node + 1.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public static boolean graphColoring(int v, List<int[]> edges, int m) {
        boolean[][] graph = new boolean[v][v];
        for (int[] edge : edges) {
            graph[edge[0]][edge[1]] = true;
            graph[edge[1]][edge[0]] = true;
        }
        int[] color = new int[v];
        return solve(0, graph, color, v, m);
    }

    private static boolean solve(int node, boolean[][] graph, int[] color, int v, int m) {
        if (node == v) return true;

        for (int c = 1; c <= m; c++) {
            if (isSafe(node, graph, color, c, v)) {
                color[node] = c;
                if (solve(node + 1, graph, color, v, m)) return true;
                color[node] = 0;
            }
        }
        return false;
    }

    private static boolean isSafe(int node, boolean[][] graph, int[] color, int c, int v) {
        for (int i = 0; i < v; i++) {
            if (graph[node][i] && color[i] == c) return false;
        }
        return true;
    }
}

// TIME COMPLEXITY = O(M^V)
// SPACE COMPLEXITY = O(V)
