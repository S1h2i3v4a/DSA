/*
QUESTION:-
Given the head of a singly linked list, return the middle node of the linked list.
If there are two middle nodes, return the second middle node.

Example:
Input: head = [1,2,3,4,5]
Output: [3,4,5]
Explanation: The middle node of the list is node 3.
*/

/*
APPROACH:-
Two-pass algorithm: First pass counts the total number of nodes N. Second pass moves N/2 steps from head to reach the middle node.
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
    public ListNode middleNode(ListNode head) {
        int n = 0;
        ListNode temp = head;
        while (temp != null) {
            n++;
            temp = temp.next;
        }
        int mid = n / 2;
        temp = head;
        for (int i = 0; i < mid; i++) {
            temp = temp.next;
        }
        return temp;
    }
}

// TIME COMPLEXITY = O(N + N/2) = O(N)
// SPACE COMPLEXITY = O(1)
