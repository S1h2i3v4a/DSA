/*
QUESTION:-
You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

Example:
Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]
Explanation: 342 + 465 = 807.
*/

/*
APPROACH:-
Dummy Node Simulation: Traverse both lists simultaneously. At each step, calculate sum = val1 + val2 + carry, create new node with sum % 10, update carry = sum / 10. Continue while l1 != null, l2 != null, or carry != 0.
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            curr.next = new ListNode(sum % 10);
            carry = sum / 10;
            curr = curr.next;
        }
        return dummy.next;
    }
}

// TIME COMPLEXITY = O(max(N, M))
// SPACE COMPLEXITY = O(1) auxiliary
