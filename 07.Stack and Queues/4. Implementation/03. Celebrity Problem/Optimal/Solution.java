/*
QUESTION:
Find the celebrity in a party of N people using matrix `mat[N][N]` in O(N) time and O(1) space.

APPROACH:
- Use two pointers: `top = 0` and `down = n - 1`.
- While `top < down`:
  - If `mat[top][down] == 1`: person `top` knows `down`, so `top` cannot be celebrity (`top++`).
  - Else: person `top` does not know `down`, so `down` cannot be celebrity (`down--`).
- Candidate is `top`.
- Verify candidate:
  - Check that candidate knows nobody (`mat[top][i] == 0` for all `i != top`).
  - Check that everybody knows candidate (`mat[i][top] == 1` for all `i != top`).
- If valid return `top`, else return -1.

TIME COMPLEXITY: O(N) elimination pass + O(N) verification pass = O(N).
SPACE COMPLEXITY: O(1) auxiliary space.
*/

class Solution {
    public static int celebrity(int[][] mat) {
        int n = mat.length;
        int top = 0, down = n - 1;

        while (top < down) {
            if (mat[top][down] == 1) {
                top++;
            } else {
                down--;
            }
        }

        int candidate = top;
        for (int i = 0; i < n; i++) {
            if (i != candidate) {
                if (mat[candidate][i] == 1 || mat[i][candidate] == 0) {
                    return -1;
                }
            }
        }
        return candidate;
    }
}
