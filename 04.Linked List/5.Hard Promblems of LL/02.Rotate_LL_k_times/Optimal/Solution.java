/*
QUESTION:-
Given the head of a linked list, rotate the list to the right by k places.

Example:
Input: head = [1,2,3,4,5], k = 2
Output: [4,5,1,2,3]
*/

/*
APPROACH:-
Make Circular & Cut: Compute length L and find tail. Connect tail.next = head to make list circular. Advance L - (k % L) steps from tail, set new head, and break the cycle.
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
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) return head;

        int length = 1;
        ListNode tail = head;
        while (tail.next != null) {
            length++;
            tail = tail.next;
        }

        k = k % length;
        if (k == 0) return head;

        tail.next = head; // make circular
        int stepsToNewTail = length - k;
        ListNode newTail = head;
        for (int i = 1; i < stepsToNewTail; i++) {
            newTail = newTail.next;
        }

        ListNode newHead = newTail.next;
        newTail.next = null; // break cycle
        return newHead;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
