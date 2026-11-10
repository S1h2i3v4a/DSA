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
Branch and Bound using Hash Arrays: Use 3 boolean arrays: cols[n], d1[2n] (row - col + n), and d2[2n] (row + col) to check queen safety in O(1) time.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        for (char[] row : board) Arrays.fill(row, '.');
        boolean[] cols = new boolean[n];
        boolean[] d1 = new boolean[2 * n];
        boolean[] d2 = new boolean[2 * n];
        backtrack(0, board, cols, d1, d2, result, n);
        return result;
    }

    private static void backtrack(int row, char[][] board, boolean[] cols, boolean[] d1, boolean[] d2, List<List<String>> result, int n) {
        if (row == n) {
            List<String> current = new ArrayList<>();
            for (char[] r : board) current.add(new String(r));
            result.add(current);
            return;
        }
        for (int col = 0; col < n; col++) {
            int id1 = row - col + n;
            int id2 = row + col;
            if (!cols[col] && !d1[id1] && !d2[id2]) {
                board[row][col] = 'Q';
                cols[col] = d1[id1] = d2[id2] = true;
                backtrack(row + 1, board, cols, d1, d2, result, n);
                board[row][col] = '.';
                cols[col] = d1[id1] = d2[id2] = false;
            }
        }
    }
}

// TIME COMPLEXITY = O(N!)
// SPACE COMPLEXITY = O(N)
