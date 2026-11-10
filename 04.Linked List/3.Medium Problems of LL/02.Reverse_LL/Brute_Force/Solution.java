/*
QUESTION:-
Given the head of a singly linked list, reverse the list, and return the reversed list.

Example:
Input: head = [1,2,3,4,5]
Output: [5,4,3,2,1]
*/

/*
APPROACH:-
Stack of Values: Push all node values onto a stack in the first pass. In the second pass, pop values from the stack and rewrite node values.
*/

import java.util.*;
import java.io.*;

// HELPER DEFINITION:-
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

// CODE:-
class Solution {
    public ListNode reverseList(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        ListNode temp = head;
        while (temp != null) {
            stack.push(temp.val);
            temp = temp.next;
        }
        temp = head;
        while (temp != null) {
            temp.val = stack.pop();
            temp = temp.next;
        }
        return head;
    }
}

// TIME COMPLEXITY = O(2N) = O(N)
// SPACE COMPLEXITY = O(N)
