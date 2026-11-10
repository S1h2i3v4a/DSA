/*
QUESTION:
Given an `m x n` matrix `board` containing 'X' and 'O', capture all regions that are 4-directionally surrounded by 'X'.
A region is captured by flipping all 'O's into 'X's in that surrounded region.

APPROACH:
- Boundary connected 'O's can never be surrounded.
- Run DFS/BFS from all boundary 'O's and mark them with a special character '#'.
- Iterate through entire board:
  - Any remaining 'O' was surrounded, so flip to 'X'.
  - Any '#' was boundary-connected, so restore to 'O'.

TIME COMPLEXITY: O(M * N).
SPACE COMPLEXITY: O(M * N) recursion stack.
*/

class Solution {
    private void dfs(char[][] board, int r, int c) {
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c] != 'O') {
            return;
        }
        board[r][c] = '#';
        dfs(board, r + 1, c);
        dfs(board, r - 1, c);
        dfs(board, r, c + 1);
        dfs(board, r, c - 1);
    }

    public void solve(char[][] board) {
        if (board == null || board.length == 0) return;
        int m = board.length, n = board[0].length;

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

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') board[i][j] = 'X';
                else if (board[i][j] == '#') board[i][j] = 'O';
            }
        }
    }
}
