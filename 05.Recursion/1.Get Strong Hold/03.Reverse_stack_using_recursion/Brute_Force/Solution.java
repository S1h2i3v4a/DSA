/*
QUESTION:-
You are given a stack St. You have to reverse the stack using recursion without using any loop or extra stack.

Example:
Input: St = [3,2,1,7,6]
Output: [6,7,1,2,3]
*/

/*
APPROACH:-
Using Auxiliary Data Structure: Pop all elements from stack into a List or Queue, then push them back in reverse order.
*/

import java.util.*;
import java.io.*;

// CODE:-
class Solution {
    static void reverse(Stack<Integer> s) {
        List<Integer> list = new ArrayList<>();
        while (!s.isEmpty()) {
            list.add(s.pop());
        }
        for (int val : list) {
            s.push(val);
        }
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(N)
