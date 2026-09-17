/**
 * Problem Name: N-Queens
 * Platform: LeetCode (51)
 * Difficulty: Hard
 * 
 * Time Complexity: O(N!)
 * Space Complexity: O(N) auxiliary arrays for columns and diagonals.
 * 
 * Approach:
 * Place queens column by column.
 * Use boolean arrays/hashsets for O(1) safety checks:
 * 1. Column occupied array: cols[row]
 * 2. Main diagonal occupied (row + col): lowerDiag[row + col]
 * 3. Anti diagonal occupied (N - 1 + col - row): upperDiag[N - 1 + col - row]
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class N_Queens {

    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }

        boolean[] leftRow = new boolean[n];
        boolean[] lowerDiagonal = new boolean[2 * n - 1];
        boolean[] upperDiagonal = new boolean[2 * n - 1];

        solve(0, board, result, leftRow, lowerDiagonal, upperDiagonal, n);
        return result;
    }

    private static void solve(int col, char[][] board, List<List<String>> result, 
                             boolean[] leftRow, boolean[] lowerDiagonal, boolean[] upperDiagonal, int n) {
        if (col == n) {
            result.add(constructBoard(board));
            return;
        }

        for (int row = 0; row < n; row++) {
            if (!leftRow[row] && !lowerDiagonal[row + col] && !upperDiagonal[n - 1 + col - row]) {
                board[row][col] = 'Q';
                leftRow[row] = true;
                lowerDiagonal[row + col] = true;
                upperDiagonal[n - 1 + col - row] = true;

                solve(col + 1, board, result, leftRow, lowerDiagonal, upperDiagonal, n);

                // Backtrack
                board[row][col] = '.';
                leftRow[row] = false;
                lowerDiagonal[row + col] = false;
                upperDiagonal[n - 1 + col - row] = false;
            }
        }
    }

    private static List<String> constructBoard(char[][] board) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            res.add(new String(board[i]));
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 4;
        List<List<String>> solutions = solveNQueens(n);
        System.out.println("N-Queens solutions for N = " + n + ":");
        for (List<String> sol : solutions) {
            for (String row : sol) {
                System.out.println(row);
            }
            System.out.println();
        }
    }
}
