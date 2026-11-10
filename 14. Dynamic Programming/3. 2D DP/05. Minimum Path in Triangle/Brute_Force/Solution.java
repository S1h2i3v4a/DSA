/*
QUESTION:
Given a triangle array, return the minimum path sum from top to bottom.
For step from (i, j), you may move to adjacent numbers on row below: (i + 1, j) or (i + 1, j + 1).

APPROACH:
- Pure recursion from top (0, 0) downward.
- solve(i, j) = triangle[i][j] + min(solve(i + 1, j), solve(i + 1, j + 1)).

TIME COMPLEXITY: O(2^N) exponential.
SPACE COMPLEXITY: O(N) recursion stack.
*/

import java.util.List;

class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        return solve(0, 0, triangle);
    }

    private int solve(int i, int j, List<List<Integer>> triangle) {
        if (i == triangle.size() - 1) {
            return triangle.get(i).get(j);
        }
        int down = triangle.get(i).get(j) + solve(i + 1, j, triangle);
        int diagonal = triangle.get(i).get(j) + solve(i + 1, j + 1, triangle);
        return Math.min(down, diagonal);
    }
}
