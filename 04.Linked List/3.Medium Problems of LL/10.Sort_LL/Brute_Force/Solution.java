/*
QUESTION:-
Given the head of a linked list, return the list after sorting it in ascending order.

Example:
Input: head = [4,2,1,3]
Output: [1,2,3,4]
*/

/*
APPROACH:-
Array Sorting: Copy all node values into an ArrayList, sort the list using Collections.sort(), and write sorted values back into nodes.
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
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        List<Integer> list = new ArrayList<>();
        ListNode temp = head;
        while (temp != null) {
            list.add(temp.val);
            temp = temp.next;
        }
        Collections.sort(list);
        temp = head;
        for (int val : list) {
            temp.val = val;
            temp = temp.next;
        }
        return head;
    }
}

// TIME COMPLEXITY = O(N log N)
// SPACE COMPLEXITY = O(N)
