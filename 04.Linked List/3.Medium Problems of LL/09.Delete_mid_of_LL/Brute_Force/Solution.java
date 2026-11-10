/*
QUESTION:-
You are given the head of a linked list. Delete the middle node, and return the head of the modified linked list.

Example:
Input: head = [1,3,4,7,1,2,6]
Output: [1,3,4,1,2,6]
Explanation: Node 7 at index 3 is removed.
*/

/*
APPROACH:-
Two-pass: First pass counts total length N. If N == 1 return null. Second pass moves N/2 - 1 steps to reach node before middle, then delete middle node.
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
    public ListNode deleteMiddle(ListNode head) {
        if (head == null || head.next == null) return null;
        int count = 0;
        ListNode temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }

        int mid = count / 2;
        temp = head;
        for (int i = 0; i < mid - 1; i++) {
            temp = temp.next;
        }
        temp.next = temp.next.next;
        return head;
    }
}

// TIME COMPLEXITY = O(N + N/2) = O(N)
// SPACE COMPLEXITY = O(1)
