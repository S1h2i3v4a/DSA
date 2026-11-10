/*
QUESTION:-
Consider a rat placed at (0, 0) in a square matrix mat of order n* n. It has to reach the destination at (n - 1, n - 1). Find all possible paths that the rat can take to reach from source to destination. The directions the rat can take are 'U'(up), 'D'(down), 'L' (left), 'R' (right). Return paths in lexicographical order.

Example:
Input: mat[][] = {{1, 0, 0, 0}, {1, 1, 0, 1}, {1, 1, 0, 0}, {0, 1, 1, 1}}
Output: DDRDRR DRDDRR
*/

/*
APPROACH:-
Backtracking in Lexicographical Order: Explore moves in order 'D', 'L', 'R', 'U'. Mark current cell as visited by setting mat[r][c] = 0, recursively explore, and restore mat[r][c] = 1 upon backtracking.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public static ArrayList<String> findPath(int[][] mat) {
        ArrayList<String> result = new ArrayList<>();
        int n = mat.length;
        if (mat[0][0] == 0 || mat[n - 1][n - 1] == 0) return result;
        solve(0, 0, mat, n, new StringBuilder(), result);
        return result;
    }

    private static void solve(int r, int c, int[][] mat, int n, StringBuilder path, ArrayList<String> result) {
        if (r == n - 1 && c == n - 1) {
            result.add(path.toString());
            return;
        }

        mat[r][c] = 0; // mark visited

        // Down
        if (r + 1 < n && mat[r + 1][c] == 1) {
            path.append('D');
            solve(r + 1, c, mat, n, path, result);
            path.deleteCharAt(path.length() - 1);
        }
        // Left
        if (c - 1 >= 0 && mat[r][c - 1] == 1) {
            path.append('L');
            solve(r, c - 1, mat, n, path, result);
            path.deleteCharAt(path.length() - 1);
        }
        // Right
        if (c + 1 < n && mat[r][c + 1] == 1) {
            path.append('R');
            solve(r, c + 1, mat, n, path, result);
            path.deleteCharAt(path.length() - 1);
        }
        // Up
        if (r - 1 >= 0 && mat[r - 1][c] == 1) {
            path.append('U');
            solve(r - 1, c, mat, n, path, result);
            path.deleteCharAt(path.length() - 1);
        }

        mat[r][c] = 1; // unmark visited
    }
}

// TIME COMPLEXITY = O(4^(N^2))
// SPACE COMPLEXITY = O(N^2)
