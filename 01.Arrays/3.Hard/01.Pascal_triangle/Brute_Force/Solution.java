/*
QUESTION:-
Given an integer numRows, return the first numRows of Pascal's triangle. In Pascal's triangle, each number is the sum of the two numbers directly above it.

Example:
Input: numRows = 5
Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
*/

/*
APPROACH:-
Compute each cell at row r and column c using the combinations formula nCr where n = r - 1 and r = c - 1 using a helper function.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int r = 1; r <= numRows; r++) {
            List<Integer> row = new ArrayList<>();
            for (int c = 1; c <= r; c++) {
                row.add(nCr(r - 1, c - 1));
            }
            ans.add(row);
        }
        return ans;
    }
    private int nCr(int n, int r) {
        long res = 1;
        for (int i = 0; i < r; i++) {
            res = res * (n - i);
            res = res / (i + 1);
        }
        return (int) res;
    }
}

// TIME COMPLEXITY = O(N^3)
// SPACE COMPLEXITY = O(1) auxiliary
