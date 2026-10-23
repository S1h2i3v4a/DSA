/*
QUESTION:-
Given the head of a linked list, return the list after sorting it in ascending order.

Example:
Input: head = [4,2,1,3]
Output: [1,2,3,4]

APPROACH:
To sort a linked list, we can use the merge sort algorithm.
1. Implement a function to merge two sorted linked lists.
2. Implement a function to recursively divide the linked list into two halves.
3. Apply merge sort on each half.
4. Merge the sorted halves using the merge function.
5. Return the head of the sorted linked list.

TIME COMPLEXITY: O(NlogN)
SPACE COMPLEXITY: O(logN) - Recursive stack space

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
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode mid = getMid(head);
        ListNode rightHead = mid.next;
        mid.next = null;

        ListNode left = sortList(head);
        ListNode right = sortList(rightHead);

        return merge(left, right);
    }

    private ListNode getMid(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        if (l1 != null) curr.next = l1;
        if (l2 != null) curr.next = l2;
        return dummy.next;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
