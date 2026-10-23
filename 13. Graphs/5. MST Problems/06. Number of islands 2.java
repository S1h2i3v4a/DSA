/*
QUESTION:-
You are given an n x m 2D matrix, and an array of size k denoting the number of operations. The matrix elements are 0 if there is water or 1 if there is land. Originally, the 2D matrix is all 0, which means there is no land in the matrix. The array has k operator(s) and each operator has two integers A[i][0], A[i][1], which means that you can change the cell matrix[A[i][0]][A[i][1]] from sea to an island. Return how many islands are there in the matrix after each operation. You need to return an array of size k.
Note: An island means a group of 1s such that they share a common side.

Example 1:
Input: n = 4, m = 5, k = 4, A = {{1,1},{0,1},{3,3},{3,4}}
Output: 1 1 2 2
Explanation:
0.  00000
    00000
    00000
    00000
1.  00000
    01000
    00000
    00000
2.  01000
    01000
    00000
    00000
3.  01000
    01000
    00000
    00010
4.  01000
    01000
    00000
    00011
*/

import java.util.*;
import java.io.*;

// CODE:-
class DisjointSet {
    int[] parent, size;
    DisjointSet(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) { parent[i] = i; size[i] = 1; }
    }
    int find(int i) {
        if (parent[i] == i) return i;
        return parent[i] = find(parent[i]);
    }
    void union(int u, int v) {
        int rootU = find(u), rootV = find(v);
        if (rootU != rootV) {
            if (size[rootU] < size[rootV]) { parent[rootU] = rootV; size[rootV] += size[rootU]; }
            else { parent[rootV] = rootU; size[rootU] += size[rootV]; }
        }
    }
}

class Solution {
    public List<Integer> numOfIslands(int rows, int cols, int[][] operators) {
        DisjointSet ds = new DisjointSet(rows * cols);
        boolean[][] vis = new boolean[rows][cols];
        List<Integer> ans = new ArrayList<>();
        int count = 0;
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};
        for (int[] op : operators) {
            int r = op[0], c = op[1];
            if (vis[r][c]) { ans.add(count); continue; }
            vis[r][c] = true;
            count++;
            int node = r * cols + c;
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i], nc = c + dc[i];
                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && vis[nr][nc]) {
                    int adjNode = nr * cols + nc;
                    if (ds.find(node) != ds.find(adjNode)) {
                        count--;
                        ds.union(node, adjNode);
                    }
                }
            }
            ans.add(count);
        }
        return ans;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
