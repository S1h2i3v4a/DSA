/*QUESTION:
Given an m x n grid of characters board and a string word, return true if word exists in the grid.

Example 1:

Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
Output: true

Example 2:

Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
Output: true

Example 3:

Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
Output: false


Approach:
We can use a backtracking approach to solve this problem.
1. Create a helper function called 'solve' that takes the current row index, column index, letter index, the target word, and the board.
2. In the 'solve' function:
   - If the letter index is equal to the length of the target word, set the flag to true and return.
   - Check if the current cell value is equal to the target letter.
   - If it is, mark the current cell as visited (by changing its value to '!') and recursively call the 'solve' function for its neighboring cells (up, down, left, and right).
   - After the recursive call, restore the original value of the current cell.
3. Create the main function called 'exist' that calculates and returns true if the word exists in the grid, false otherwise.
   - Initialize a flag variable to false.
   - Iterate through each cell in the grid.
   - If the current cell value is equal to the first letter of the target word, mark the current cell as visited (by changing its value to '!') and call the 'solve' function with the current cell's coordinates, letter index as 1, the target word, and the board.
   - If the flag is true, return true (word exists in the grid).
   - If the loop completes without finding the word, return false.
   
Time Complexity: O(M * N * 4^L), where M is the number of rows, N is the number of columns in the grid, and L is the length of the target word. In the worst case, we traverse the entire grid for each letter in the target word, and we have 4 choices (up, down, left, right) at each step.
Space Complexity: O(L), where L is the length of the target word. The space is used for the recursive call stack.

CODE:*/

import java.util.*;
import java.io.*;

// CODE:-
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
