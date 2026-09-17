/**
 * Problem Name: Word Search
 * Platform: LeetCode (79)
 * Difficulty: Medium
 * 
 * Time Complexity: O(M * N * 4^L) where M x N is grid size and L is word length.
 * Space Complexity: O(L) recursion stack depth.
 * 
 * Approach:
 * Grid DFS with Backtracking.
 * We search for the first character on board. If matched, we explore 4 directions (Up, Down, Left, Right).
 * Temporarily mark board[r][c] with '#' to avoid revisited cells, then restore it (backtrack).
 */

class Word_Search {

    public static boolean exist(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (board[r][c] == word.charAt(0)) {
                    if (dfs(board, word, r, c, 0)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean dfs(char[][] board, String word, int r, int c, int index) {
        if (index == word.length()) {
            return true;
        }

        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c] != word.charAt(index)) {
            return false;
        }

        char temp = board[r][c];
        board[r][c] = '#'; // Mark visited

        int[] dRow = {-1, 1, 0, 0};
        int[] dCol = {0, 0, -1, 1};

        for (int i = 0; i < 4; i++) {
            if (dfs(board, word, r + dRow[i], c + dCol[i], index + 1)) {
                board[r][c] = temp; // Restore board
                return true;
            }
        }

        board[r][c] = temp; // Backtrack
        return false;
    }

    public static void main(String[] args) {
        char[][] board = {
            {'A', 'B', 'C', 'E'},
            {'S', 'F', 'C', 'S'},
            {'A', 'D', 'E', 'E'}
        };
        String word = "ABCCED";
        System.out.println("Does word \"" + word + "\" exist on board? " + exist(board, word));
    }
}
