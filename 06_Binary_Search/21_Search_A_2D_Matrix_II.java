/*
 * Problem Name: Search a 2D Matrix II
 * Platform: LeetCode (240) / GeeksforGeeks
 * Difficulty: Medium
 * Time Complexity: O(M + N)
 * Space Complexity: O(1)
 */

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;
        int row = 0, col = matrix[0].length - 1;

        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == target) return true;
            else if (matrix[row][col] > target) col--;
            else row++;
        }
        return false;
    }
}
