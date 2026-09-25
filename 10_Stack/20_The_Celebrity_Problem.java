/**
 * Problem Name: The Celebrity Problem
 * Platform: GeeksforGeeks
 * Difficulty: Medium
 * 
 * Time Complexity: O(N) where N is the number of people.
 * Space Complexity: O(1) auxiliary space using Two Pointers.
 * 
 * Approach:
 * Two Pointers Candidate Elimination.
 * 1. Initialize `top = 0`, `down = N - 1`.
 * 2. While `top < down`:
 *    - If `mat[top][down] == 1` (top knows down), `top` cannot be celebrity -> `top++`.
 *    - Else (top does not know down), `down` cannot be celebrity -> `down--`.
 * 3. Verify candidate `top`:
 *    - `mat[top][i] == 0` for all `i` (celebrity knows no one).
 *    - `mat[i][top] == 1` for all `i != top` (everyone knows celebrity).
 * Return candidate `top` if valid, else -1.
 */

class The_Celebrity_Problem {

    public static int celebrity(int[][] mat) {
        int n = mat.length;
        int top = 0;
        int down = n - 1;

        while (top < down) {
            if (mat[top][down] == 1) {
                top++;
            } else {
                down--;
            }
        }

        int candidate = top;

        // Verification phase
        for (int i = 0; i < n; i++) {
            if (i != candidate) {
                if (mat[candidate][i] == 1 || mat[i][candidate] == 0) {
                    return -1;
                }
            }
        }

        return candidate;
    }

    public static void main(String[] args) {
        int[][] mat = {
            {0, 1, 0},
            {0, 0, 0},
            {0, 1, 0}
        };
        System.out.println("Celebrity Index: " + celebrity(mat));
    }
}
