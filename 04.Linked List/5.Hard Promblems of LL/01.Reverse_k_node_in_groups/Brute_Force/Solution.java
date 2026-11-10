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
Array of k elements: Check if k nodes exist. If so, copy k nodes into an array, reverse their values, write back, and advance k steps.
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
        while (temp != null) {
            ListNode check = temp;
            int count = 0;
            while (check != null && count < k) {
                count++;
                check = check.next;
            }
            if (count == k) {
                int[] arr = new int[k];
                ListNode curr = temp;
                for (int i = 0; i < k; i++) {
                    arr[i] = curr.val;
                    curr = curr.next;
                }
                curr = temp;
                for (int i = k - 1; i >= 0; i--) {
                    curr.val = arr[i];
                    curr = curr.next;
                }
                temp = check;
            } else {
                break;
            }
        }
        return head;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(k)
