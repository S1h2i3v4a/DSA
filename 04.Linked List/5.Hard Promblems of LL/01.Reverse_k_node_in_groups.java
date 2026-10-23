/*
QUESTION:-
Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list.

APPROACH:
The idea is to reverse the nodes of the linked list in groups of size k.
First, we need to check if there are at least k nodes remaining in the linked list. If not, we return the head as it is.
Next, we reverse the first k nodes of the linked list. To do this, we maintain three pointers: prev, curr, and frwd.
We iterate through the first k nodes and at each step, we reverse the links between the nodes.
After reversing the first k nodes, prev becomes the new head, and curr becomes the new tail of the reversed group.
We recursively call the function on the remaining linked list starting from frwd (which points to the (k+1)-th node).
Finally, we update the next pointer of the original head to point to the reversed group obtained from the recursive call.

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
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy, nex = dummy, pre = dummy;

        int count = 0;
        while (cur.next != null) {
            cur = cur.next;
            count++;
        }

        while (count >= k) {
            cur = pre.next;
            nex = cur.next;
            for (int i = 1; i < k; i++) {
                cur.next = nex.next;
                nex.next = pre.next;
                pre.next = nex;
                nex = cur.next;
            }
            pre = cur;
            count -= k;
        }

        return dummy.next;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
