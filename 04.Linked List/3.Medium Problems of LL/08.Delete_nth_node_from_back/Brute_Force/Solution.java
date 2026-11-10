/*
QUESTION:-
Given the head of a linked list, remove the nth node from the end of the list and return its head.

Example:
Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]
*/

/*
APPROACH:-
Two-pass method: First pass calculates the total length L of the linked list. If n == L, return head.next. Otherwise traverse L - n steps and delete node.
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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int length = 0;
        ListNode temp = head;
        while (temp != null) {
            length++;
            temp = temp.next;
        }

        if (n == length) return head.next;

        temp = head;
        for (int i = 1; i < length - n; i++) {
            temp = temp.next;
        }
        temp.next = temp.next.next;
        return head;
    }
}

// TIME COMPLEXITY = O(2L) = O(N)
// SPACE COMPLEXITY = O(1)
