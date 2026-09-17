/**
 * Problem Name: Rat in a Maze Problem - I
 * Platform: GeeksforGeeks
 * Difficulty: Medium
 * 
 * Time Complexity: O(4^(N^2)) worst case path combinations.
 * Space Complexity: O(N^2) auxiliary recursion stack depth.
 * 
 * Approach:
 * Backtracking Grid Traversal.
 * Starting at cell (0,0), move in directions Down ('D'), Left ('L'), Right ('R'), Up ('U') in lexicographical order.
 * Maintain visited status to prevent cycles, and backtrack after recursive calls.
 */

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
