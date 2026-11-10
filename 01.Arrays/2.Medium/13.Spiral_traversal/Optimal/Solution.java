/*
QUESTION:-
Given an m x n matrix, return all elements of the matrix in spiral order.

Example:
Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,2,3,6,9,8,7,4,5]
*/

/*
APPROACH:-
Maintain 4 boundary pointers: top, bottom, left, right. Traverse top row (left to right), right col (top to bottom), bottom row (right to left, if top <= bottom), and left col (bottom to top, if left <= right). Narrow boundaries inwards.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        int top = 0, bottom = matrix.length - 1;
        int left = 0, right = matrix[0].length - 1;
        while (top <= bottom && left <= right) {
            for (int i = left; i <= right; i++) ans.add(matrix[top][i]);
            top++;
            for (int i = top; i <= bottom; i++) ans.add(matrix[i][right]);
            right--;
            if (top <= bottom) {
                for (int i = right; i >= left; i--) ans.add(matrix[bottom][i]);
                bottom--;
            }
            if (left <= right) {
                for (int i = bottom; i >= top; i--) ans.add(matrix[i][left]);
                left++;
            }
        }
        return ans;
    }
}

// TIME COMPLEXITY = O(M * N)
// SPACE COMPLEXITY = O(1) auxiliary
