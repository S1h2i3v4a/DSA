/*
QUESTION:-
A number N is represented in Linked List such that each digit corresponds to a node in linked list. You need to add 1 to it.

Example:
Input: LinkedList: 4->5->6
Output: 4->5->7
*/

/*
APPROACH:-
Recursive Backtracking: Use recursion to reach the last node, add 1, and return carry back up the call stack. If a carry remains after returning to head, create a new head node.
*/

import java.util.*;
import java.io.*;

// HELPER DEFINITION:-
class Node {
    int data;
    Node next;
    Node(int x) { data = x; next = null; }
}

// CODE:-
class Solution {
    public static Node addOne(Node head) {
        int carry = helper(head);
        if (carry > 0) {
            Node newHead = new Node(carry);
            newHead.next = head;
            return newHead;
        }
        return head;
    }

    private static int helper(Node node) {
        if (node == null) return 1;
        int carry = helper(node.next);
        int sum = node.data + carry;
        node.data = sum % 10;
        return sum / 10;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(N) (call stack)
