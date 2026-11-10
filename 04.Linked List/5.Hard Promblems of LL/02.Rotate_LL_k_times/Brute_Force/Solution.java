/*
QUESTION:-
Given the head of a linked list, rotate the list to the right by k places.

Example:
Input: head = [1,2,3,4,5], k = 2
Output: [4,5,1,2,3]
*/

/*
APPROACH:-
Rotate 1 step k times: In each of the k rotations, find the second to last node, move the last node to head.
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

        int len = 0;
        ListNode temp = head;
        while (temp != null) {
            len++;
            temp = temp.next;
        }
        k = k % len;

        for (int i = 0; i < k; i++) {
            ListNode prev = null, curr = head;
            while (curr.next != null) {
                prev = curr;
                curr = curr.next;
            }
            prev.next = null;
            curr.next = head;
            head = curr;
        }
        return head;
    }
}

// TIME COMPLEXITY = O(k * N)
// SPACE COMPLEXITY = O(1)
