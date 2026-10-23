/*
QUESTION:-
Given the head of a linked list, rotate the list to the right by k places.

APPROACH:
To rotate the linked list to the right by k places, we need to perform the following steps:
1. Find the length of the linked list and connect the last node to the head to form a circular linked list.
2. Calculate the actual number of rotations by taking the modulus of k with the length of the linked list.
3. Traverse to the (length - k - 1)th node, which will be the new tail of the rotated list.
4. Set the new head as the next node of the (length - k - 1)th node and disconnect it from the rest of the list.
5. Return the new head.

TIME COMPLEXITY: O(N), where N is the number of nodes in the linked list.
SPACE COMPLEXITY: O(1).

*/

import java.util.*;
import java.io.*;

// CODE:-
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; next = null; }
}

class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) return head;

        ListNode tail = head;
        int length = 1;
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }

        tail.next = head;
        k = k % length;
        int stepsToNewHead = length - k;
        ListNode newTail = tail;

        while (stepsToNewHead > 0) {
            newTail = newTail.next;
            stepsToNewHead--;
        }

        ListNode newHead = newTail.next;
        newTail.next = null;

        return newHead;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
