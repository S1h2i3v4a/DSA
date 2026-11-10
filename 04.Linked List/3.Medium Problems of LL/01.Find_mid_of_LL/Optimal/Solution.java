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
Tortoise and Hare (Slow & Fast pointers): Maintain slow and fast pointers. Slow moves 1 step while fast moves 2 steps. When fast reaches the end, slow is at the middle node.
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
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}

// TIME COMPLEXITY = O(N/2) = O(N)
// SPACE COMPLEXITY = O(1)
