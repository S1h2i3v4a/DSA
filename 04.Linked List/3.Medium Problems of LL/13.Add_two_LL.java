/*
QUESTION:-
You are given two non-empty linked lists representing two non-negative integers. 
The digits are stored in reverse order, and each of their nodes contains a single digit. 
Add the two numbers and return the sum as a linked list.

APPROACH:
Traverse both linked lists simultaneously, starting from the heads.
At each step, add the corresponding digits from both linked lists along with the carry (initialized as 0).
Create a new node with the sum%10 and update the carry as sum/10.
Move to the next nodes in both linked lists.
Continue this process until both linked lists are traversed completely and there is no carry left.
If one linked list is shorter than the other, consider its remaining digits as 0.
If there is still a remaining carry, create a new node with the carry and append it to the result linked list.
Return the head of the result linked list.

TIME COMPLEXITY: O(max(N, M)), where N and M are the lengths of the two input linked lists.
SPACE COMPLEXITY: O(max(N, M)), as the length of the result linked list can be at most max(N, M)+1.

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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            int x = (l1 != null) ? l1.val : 0;
            int y = (l2 != null) ? l2.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        return dummy.next;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
