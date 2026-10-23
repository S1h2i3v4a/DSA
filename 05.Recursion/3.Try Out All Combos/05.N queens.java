/**
 * Question: The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 *
Example 1:


Input: n = 4
Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above

 * Approach:
 * 1. Use backtracking to solve the n-queens puzzle.
 * 2. Start with an empty chessboard and try placing queens in each row, ensuring that no two queens threaten each other.
 * 3. Use row, column, and diagonal constraints to validate the queen placements.
 * 4. If a valid configuration is found, add it to the list of solutions.
 *
 * Time Complexity: O(N!), where N is the input parameter representing the size of the chessboard.
 *   - In the worst case, there can be N! possible configurations to check.
 *   - However, with the pruning technique used in backtracking, the actual runtime is much less than N! in most cases.
 *
 * Space Complexity: O(N^2), where N is the input parameter representing the size of the chessboard.
 *   - The space is used to store the chessboard configuration and the auxiliary arrays for tracking the used columns and diagonals.
 */

import java.util.*;
import java.io.*;

// CODE:-
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
