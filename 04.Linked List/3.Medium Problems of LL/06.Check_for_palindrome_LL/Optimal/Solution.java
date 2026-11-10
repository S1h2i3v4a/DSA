/*
QUESTION:-
Given the head of a singly linked list, return true if it is a palindrome or false otherwise.

Example:
Input: head = [1,2,2,1]
Output: true
*/

/*
APPROACH:-
Reverse Second Half in-place: Find the middle using slow and fast pointers. Reverse the second half of the list. Compare the first half and reversed second half. Restore the list before returning.
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
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;

        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode secondHalf = reverse(slow.next);
        ListNode p1 = head, p2 = secondHalf;
        boolean isPal = true;
        while (p2 != null) {
            if (p1.val != p2.val) {
                isPal = false;
                break;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        reverse(secondHalf); // restore original list
        return isPal;
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
