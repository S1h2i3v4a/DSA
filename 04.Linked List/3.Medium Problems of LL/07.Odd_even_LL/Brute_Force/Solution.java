/*
QUESTION:-
Given the head of a singly linked list, group all the nodes with odd indices together followed by the nodes with even indices, and return the reordered list.
The first node is considered odd, and the second node is even, and so on.

Example:
Input: head = [1,2,3,4,5]
Output: [1,3,5,2,4]
*/

/*
APPROACH:-
Two Lists / ArrayLists: Collect values of odd-positioned nodes into one list, and even-positioned nodes into another. Overwrite list values in order.
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
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) return head;
        List<Integer> odds = new ArrayList<>();
        List<Integer> evens = new ArrayList<>();

        ListNode temp = head;
        int idx = 1;
        while (temp != null) {
            if (idx % 2 == 1) odds.add(temp.val);
            else evens.add(temp.val);
            idx++;
            temp = temp.next;
        }

        temp = head;
        for (int val : odds) {
            temp.val = val;
            temp = temp.next;
        }
        for (int val : evens) {
            temp.val = val;
            temp = temp.next;
        }
        return head;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(N)
