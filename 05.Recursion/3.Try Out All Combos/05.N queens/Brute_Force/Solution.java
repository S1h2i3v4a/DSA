/*
QUESTION:-
The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
Given an integer n, return all distinct solutions to the n-queens puzzle.

Example:
Input: n = 4
Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
*/

/*
APPROACH:-
Backtracking with Board Scan: Place queens row by row. Before placing a queen at (row, col), scan column, top-left diagonal, and top-right diagonal in O(N).
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        for (char[] row : board) Arrays.fill(row, '.');
        solve(0, board, result, n);
        return result;
    }

    private static void solve(int row, char[][] board, List<List<String>> result, int n) {
        if (row == n) {
            List<String> current = new ArrayList<>();
            for (char[] r : board) current.add(new String(r));
            result.add(current);
            return;
        }
        for (int col = 0; col < n; col++) {
            if (isSafe(board, row, col, n)) {
                board[row][col] = 'Q';
                solve(row + 1, board, result, n);
                board[row][col] = '.';
            }
        }
    }

    private static boolean isSafe(char[][] board, int row, int col, int n) {
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') return false;
        }
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') return false;
        }
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') return false;
        }
        return true;
    }
}

// TIME COMPLEXITY = O(N! * N)
// SPACE COMPLEXITY = O(N^2)
