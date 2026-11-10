/*
QUESTION:-
Given an integer numRows, return the first numRows of Pascal's triangle. In Pascal's triangle, each number is the sum of the two numbers directly above it.

Example:
Input: numRows = 5
Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
*/

/*
APPROACH:-
Generate each row in O(row) time using the property: element[c] = element[c-1] * (row - c) / c. Append 1 at start and end of each row.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            long val = 1;
            row.add(1);
            for (int j = 1; j <= i; j++) {
                val = val * (i - j + 1) / j;
                row.add((int) val);
            }
            ans.add(row);
        }
        return ans;
    }
}

// TIME COMPLEXITY = O(N^2)
// SPACE COMPLEXITY = O(1) auxiliary
