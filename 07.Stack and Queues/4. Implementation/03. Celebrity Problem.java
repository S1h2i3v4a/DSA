/*
QUESTION:
Given a square NxN matrix representing people at a party, determine if there is a celebrity in the party. A celebrity is defined as a person who is known to all but does not know anyone at the party.

APPROACH:
To solve this problem, we can use a stack to keep track of potential celebrity candidates.
1. Initially, push all the people (indices) onto the stack.
2. While the stack size is greater than 1, do the following:
   - Pop two people 'a' and 'b' from the stack.
   - Check if 'a' knows 'b' and 'b' does not know 'a'. If true, push 'b' back onto the stack.
   - Check if 'b' knows 'a' and 'a' does not know 'b'. If true, push 'a' back onto the stack.
3. After the above loop, if the stack is empty, there is no celebrity, so return -1.
4. Otherwise, get the potential celebrity from the top of the stack.
5. Check if the potential celebrity is known by everyone else and does not know anyone else.
   - If true, return the potential celebrity index.
   - If false, return -1.

Example:
Input: N = 3, M[][] = {{0, 1, 0}, {0, 0, 0}, {0, 1, 0}}
Output: 1
Explanation: Both person 0 and person 2 know person 1. Therefore, person 1 is the celebrity.

CODE:
*/

import java.util.*;
import java.io.*;

// CODE:-
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
