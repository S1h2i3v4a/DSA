/*QUESTION:
Consider a rat placed at (0, 0) in a square matrix of order N * N. It has to reach the destination at (N - 1, N - 1). Find all possible paths that the rat can take to reach from source to destination. The directions in which the rat can move are 'U'(up), 'D'(down), 'L' (left), 'R' (right). Value 0 at a cell in the matrix represents that it is blocked and rat cannot move to it while value 1 at a cell in the matrix represents that rat can be travel through it.

Example 1:

Input:
N = 4
m[][] = {{1, 0, 0, 0},
         {1, 1, 0, 1}, 
         {1, 1, 0, 0},
         {0, 1, 1, 1}}
Output:
DDRDRR DRDDRR
Explanation:
The rat can reach the destination at 
(3, 3) from (0, 0) by two paths - DRDDRR 
and DDRDRR, when printed in sorted order 
we get DDRDRR DRDDRR.

Approach:
We can use a backtracking approach to find all possible paths.
1. Create a helper function called 'solve' that takes the current row index, column index, a string representing the current path, a vector of strings to store the paths, the matrix, and the size of the matrix.
2. In the 'solve' function:
   - If the current position is the destination (N-1, N-1), add the current path to the vector of paths and return.
   - Check if the current position is valid and not a blocked cell.
   - Mark the current position as visited (by setting its value to 0) to avoid revisiting it.
   - Recursively call the 'solve' function for the neighboring cells (left, right, up, down) with the updated path.
   - After the recursive calls, restore the original value of the current position to allow other paths to visit it.
3. Create the main function called 'findPath' that calculates and returns all possible paths from (0, 0) to (N-1, N-1).
   - If the source cell or the destination cell is blocked, return an empty vector.
   - Initialize an empty vector of strings to store the paths.
   - Call the 'solve' function with the initial position (0, 0), an empty path string, the vector of paths, the matrix, and the size of the matrix.
   - Return the vector of paths.
   
Time Complexity: O(3^(N^2)), where N is the size of the matrix. In the worst case, each cell can have three possible neighboring cells to explore.
Space Complexity: O(N^2), as we are using a vector of strings to store the paths.

CODE:*/

import java.util.*;
import java.io.*;

// CODE:-
import java.util.ArrayList;
import java.util.Collections;

class Rat_In_A_Maze {

    public static ArrayList<String> findPath(int[][] mat) {
        ArrayList<String> result = new ArrayList<>();
        int n = mat.length;
        if (mat[0][0] == 0 || mat[n - 1][n - 1] == 0) {
            return result;
        }

        boolean[][] visited = new boolean[n][n];
        solve(0, 0, mat, n, "", result, visited);
        Collections.sort(result);
        return result;
    }

    private static void solve(int r, int c, int[][] mat, int n, String path, 
                             ArrayList<String> result, boolean[][] visited) {
        if (r == n - 1 && c == n - 1) {
            result.add(path);
            return;
        }

        visited[r][c] = true;

        // Direction vectors: Down, Left, Right, Up
        int[] dRow = {1, 0, 0, -1};
        int[] dCol = {0, -1, 1, 0};
        char[] dChar = {'D', 'L', 'R', 'U'};

        for (int i = 0; i < 4; i++) {
            int nextR = r + dRow[i];
            int nextC = c + dCol[i];

            if (nextR >= 0 && nextR < n && nextC >= 0 && nextC < n 
                && mat[nextR][nextC] == 1 && !visited[nextR][nextC]) {
                solve(nextR, nextC, mat, n, path + dChar[i], result, visited);
            }
        }

        visited[r][c] = false; // Backtrack
    }

    public static void main(String[] args) {
        int[][] mat = {
            {1, 0, 0, 0},
            {1, 1, 0, 1},
            {1, 1, 0, 0},
            {0, 1, 1, 1}
        };

        ArrayList<String> paths = findPath(mat);
        System.out.println("Rat in a Maze Paths: " + paths);
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
