/*
QUESTION:-
Given an m x n grid of characters board and a string word, return true if word exists in the grid.
The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.

Example:
Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
Output: true
*/

/*
APPROACH:-
DFS Backtracking: Start DFS from every cell matching word.charAt(0). In DFS, temporarily replace the cell char with '#' to mark visited, explore 4 neighbors (up, down, left, right), and backtrack by restoring the original character.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public static boolean exist(char[][] board, String word) {
        int rows = board.length, cols = board[0].length;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (board[r][c] == word.charAt(0) && dfs(board, word, r, c, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean dfs(char[][] board, String word, int r, int c, int idx) {
        if (idx == word.length()) return true;
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c] != word.charAt(idx)) {
            return false;
        }
        char temp = board[r][c];
        board[r][c] = '#';

        boolean found = dfs(board, word, r + 1, c, idx + 1) ||
                        dfs(board, word, r - 1, c, idx + 1) ||
                        dfs(board, word, r, c + 1, idx + 1) ||
                        dfs(board, word, r, c - 1, idx + 1);

        board[r][c] = temp;
        return found;
    }
}

// TIME COMPLEXITY = O(N * M * 4^L) where L is word length
// SPACE COMPLEXITY = O(L) (call stack)
