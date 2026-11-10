/*
QUESTION:-
Given a row-wise sorted matrix of size R x C where R and C are always odd, find the median of the matrix.

Example:
Input: R = 3, C = 3, matrix = [[1, 3, 5], [2, 6, 9], [3, 6, 9]]
Output: 5
Explanation: Sorted elements are [1, 2, 3, 3, 5, 6, 6, 9, 9]. Median is 5.
*/

/*
APPROACH:-
Flatten & Sort: Copy all R*C elements into an array, sort the array, and return the element at index (R*C)/2.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    int median(int matrix[][], int R, int C) {
        int[] arr = new int[R * C];
        int idx = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                arr[idx++] = matrix[i][j];
            }
        }
        Arrays.sort(arr);
        return arr[(R * C) / 2];
    }
}

// TIME COMPLEXITY = O((R * C) * log(R * C))
// SPACE COMPLEXITY = O(R * C)
