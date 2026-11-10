/*
QUESTION:-
Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.

Example:
Input: head = [3,2,0,-4], pos = 1
Output: tail connects to node index 1
*/

/*
APPROACH:-
Floyd's Algorithm Phase 2: Find collision point between slow (1 step) and fast (2 steps). Reset slow to head, then advance both slow and fast 1 step at a time until they meet. The meeting node is the cycle start.
*/

import java.util.*;
import java.io.*;

// HELPER DEFINITION:-
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; next = null; }
}

// CODE:-
class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
