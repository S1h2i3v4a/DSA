/**
 * Problem Name: Surrounded Regions
 * Platform: LeetCode (LC 130)
 * Difficulty: Medium
 * 
 * Time Complexity: O(M * N) where M is rows and N is columns
 * Space Complexity: O(M * N) recursion stack space
 * 
 * Approach:
 * Boundary Traversal DFS:
 * Any 'O' connected to the border boundary of the board can NEVER be captured.
 * 1. Start DFS from all 'O' cells located on the 4 borders (first/last row, first/last column).
 * 2. Mark all reachable 'O' cells with a temporary marker '#' (uncapturable).
 * 3. Traverse entire board:
 *    - If cell is 'O' (not connected to border): change to 'X' (captured).
 *    - If cell is '#' (connected to border): restore back to 'O'.
 */

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
