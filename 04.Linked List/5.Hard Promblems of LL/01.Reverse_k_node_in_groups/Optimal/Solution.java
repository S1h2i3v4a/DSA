/*
QUESTION:-
Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list.
k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.

Example:
Input: head = [1,2,3,4,5], k = 2
Output: [2,1,4,3,5]
*/

/*
APPROACH:-
In-place Pointer Reversal: Find the k-th node. If it exists, reverse the k nodes in-place, connect with previous group, and recursively/iteratively continue for the next group.
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
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode temp = head;
        ListNode prevLast = null;

        while (temp != null) {
            ListNode kThNode = getKthNode(temp, k);
            if (kThNode == null) {
                if (prevLast != null) prevLast.next = temp;
                break;
            }
            ListNode nextNode = kThNode.next;
            kThNode.next = null;
            reverse(temp);

            if (temp == head) {
                head = kThNode;
            } else {
                prevLast.next = kThNode;
            }
            prevLast = temp;
            temp = nextNode;
        }
        return head;
    }

    private ListNode getKthNode(ListNode temp, int k) {
        k -= 1;
        while (temp != null && k > 0) {
            k--;
            temp = temp.next;
        }
        return temp;
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null, curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
