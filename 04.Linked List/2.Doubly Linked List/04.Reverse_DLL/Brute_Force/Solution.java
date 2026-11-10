/*
QUESTION:-
Given a doubly linked list of n elements. The task is to reverse the doubly linked list.

Example:
Input: LinkedList: 3 <--> 4 <--> 5
Output: 5 <--> 4 <--> 3
*/

/*
APPROACH:-
Stack of Data: Traverse DLL and push all node values onto a stack. Traverse DLL a second time, popping values and replacing node data.
*/

import java.util.*;
import java.io.*;

// HELPER DEFINITION:-
class DLLNode {
    int data;
    DLLNode next;
    DLLNode prev;
    DLLNode(int val) {
        data = val;
        next = null;
        prev = null;
    }
}

// CODE:-
class Solution {
    public DLLNode reverseDLL(DLLNode head) {
        if (head == null || head.next == null) return head;
        Stack<Integer> stack = new Stack<>();
        DLLNode curr = head;
        while (curr != null) {
            stack.push(curr.data);
            curr = curr.next;
        }
        curr = head;
        while (curr != null) {
            curr.data = stack.pop();
            curr = curr.next;
        }
        return head;
    }
}

// TIME COMPLEXITY = O(2N) = O(N)
// SPACE COMPLEXITY = O(N)
