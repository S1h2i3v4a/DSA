/*
QUESTION:-
Given an m x n matrix board containing 'X' and 'O', capture all regions that are 4-directionally surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

APPROACH:
- We can use Depth-First Search (DFS) to find all regions that are surrounded by 'X'.
- First, we initialize a copy of the board called 'vis' to store the visited status of each cell.
- Then, we perform a DFS starting from all border cells that have 'O's. During the DFS, we mark all connected 'O's as visited by changing them to a special character, such as '#', in the 'vis' matrix.
- After performing DFS from border cells, all remaining '#'s in the 'vis' matrix represent regions that are not surrounded by 'X'.
- Finally, we update the original board by flipping all remaining 'O's to 'X'.

COMPLEXITY ANALYSIS:
- Time Complexity: O(m * n), where m is the number of rows and n is the number of columns in the matrix. In the worst case, we may need to visit all the cells of the matrix during DFS.
- Space Complexity: O(m * n), where m is the number of rows and n is the number of columns in the matrix. We use additional space for the 'vis' matrix.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Surrounded_Regions {
    public void solve(char[][] board) {
        if (board == null || board.length == 0) return;

        int m = board.length;
        int n = board[0].length;

        // Traverse first and last columns
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') dfs(board, i, 0);
            if (board[i][n - 1] == 'O') dfs(board, i, n - 1);
        }

        // Traverse first and last rows
        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O') dfs(board, 0, j);
            if (board[m - 1][j] == 'O') dfs(board, m - 1, j);
        }

        // Replace surrounded 'O' with 'X', and '#' back to 'O'
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void dfs(char[][] board, int r, int c) {
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c] != 'O') {
            return;
        }

        board[r][c] = '#'; // Mark as border-connected

        dfs(board, r + 1, c);
        dfs(board, r - 1, c);
        dfs(board, r, c + 1);
        dfs(board, r, c - 1);
    }

    public static void main(String[] args) {
        Surrounded_Regions solver = new Surrounded_Regions();
        char[][] board = {
            {'X', 'X', 'X', 'X'},
            {'X', 'O', 'O', 'X'},
            {'X', 'X', 'O', 'X'},
            {'X', 'O', 'X', 'X'}
        };

        solver.solve(board);
        System.out.println("Board after capturing surrounded regions:");
        for (char[] row : board) {
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
        // Expected:
        // X X X X
        // X X X X
        // X X X X
        // X O X X
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
