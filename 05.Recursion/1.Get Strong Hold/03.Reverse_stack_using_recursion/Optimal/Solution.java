/*
QUESTION:-
You are given a stack St. You have to reverse the stack using recursion without using any loop or extra stack.

Example:
Input: St = [3,2,1,7,6]
Output: [6,7,1,2,3]
*/

/*
APPROACH:-
Pure Recursion with insertAtBottom: Hold elements in call stack by popping top element, recursively reverse remaining stack, and insert popped element at the bottom of the reversed stack using another recursive helper insertAtBottom.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    static void reverse(Stack<Integer> s) {
        if (s.isEmpty()) return;
        int top = s.pop();
        reverse(s);
        insertAtBottom(s, top);
    }

    private static void insertAtBottom(Stack<Integer> s, int x) {
        if (s.isEmpty()) {
            s.push(x);
            return;
        }
        int top = s.pop();
        insertAtBottom(s, x);
        s.push(top);
    }
}

// TIME COMPLEXITY = O(N^2)
// SPACE COMPLEXITY = O(N) (call stack)
