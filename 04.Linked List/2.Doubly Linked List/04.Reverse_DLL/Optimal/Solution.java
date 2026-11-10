/*
QUESTION:-
Given a doubly linked list of n elements. The task is to reverse the doubly linked list.

Example:
Input: LinkedList: 3 <--> 4 <--> 5
Output: 5 <--> 4 <--> 3
*/

/*
APPROACH:-
Swap Pointers: Traverse the list and swap the next and prev pointers of each node. The new head will be the last processed non-null node.
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
        DLLNode current = head;
        DLLNode last = null;

        while (current != null) {
            last = current.prev;
            current.prev = current.next;
            current.next = last;
            current = current.prev; // moves forward in original direction
        }
        return last.prev;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
