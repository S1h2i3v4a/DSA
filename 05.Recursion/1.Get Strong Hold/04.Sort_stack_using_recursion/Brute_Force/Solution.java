/*
QUESTION:-
Given a stack, the task is to sort it such that the top of the stack has the greatest element using recursion without loops.

Example:
Input: Stack: 3 2 1
Output: 3 2 1 (with 3 at top)
*/

/*
APPROACH:-
Collection Sort: Pop all elements into an ArrayList, sort the list, and push them back into stack.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    public Stack<Integer> sort(Stack<Integer> s) {
        List<Integer> list = new ArrayList<>();
        while (!s.isEmpty()) {
            list.add(s.pop());
        }
        Collections.sort(list);
        for (int val : list) {
            s.push(val);
        }
        return s;
    }
}

// TIME COMPLEXITY = O(N log N)
// SPACE COMPLEXITY = O(N)
