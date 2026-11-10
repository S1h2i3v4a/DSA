/*
QUESTION:-
Given the head of a singly linked list, return true if it is a palindrome or false otherwise.

Example:
Input: head = [1,2,2,1]
Output: true
*/

/*
APPROACH:-
Array Copy & Two Pointers: Copy all node values into an ArrayList. Use two pointers (start and end) to verify that values match from both sides.
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
        List<Integer> list = new ArrayList<>();
        ListNode temp = head;
        while (temp != null) {
            list.add(temp.val);
            temp = temp.next;
        }
        int left = 0, right = list.size() - 1;
        while (left < right) {
            if (!list.get(left).equals(list.get(right))) return false;
            left++;
            right--;
        }
        return true;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(N)
