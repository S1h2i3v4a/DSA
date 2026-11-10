/*
QUESTION:-
Given a stack, the task is to sort it such that the top of the stack has the greatest element using recursion without loops.

Example:
Input: Stack: 3 2 1
Output: 3 2 1 (with 3 at top)
*/

/*
APPROACH:-
Pure Recursion with sortedInsert: Pop top element, recursively sort remaining stack, then call sortedInsert to place the popped element in its correct position in the sorted stack.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public Stack<Integer> sort(Stack<Integer> s) {
        if (!s.isEmpty()) {
            int x = s.pop();
            sort(s);
            sortedInsert(s, x);
        }
        return s;
    }

    private void sortedInsert(Stack<Integer> s, int x) {
        if (s.isEmpty() || x > s.peek()) {
            s.push(x);
            return;
        }
        int temp = s.pop();
        sortedInsert(s, x);
        s.push(temp);
    }
}

// TIME COMPLEXITY = O(N^2)
// SPACE COMPLEXITY = O(N) (call stack)
