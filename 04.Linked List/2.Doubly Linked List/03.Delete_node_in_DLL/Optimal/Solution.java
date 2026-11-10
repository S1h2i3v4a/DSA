/*
QUESTION:-
Given a doubly linked list and a position. The task is to delete a node from given position in a doubly linked list (1-based indexing).

Example:
Input: LinkedList = 1 <--> 3 <--> 4, x = 3
Output: 1 <--> 3
*/

/*
APPROACH:-
Pointer adjustment: If deleting head (x == 1), update head = head.next and head.prev = null. Otherwise traverse to the x-th node, wire temp.prev.next = temp.next and if temp.next != null, temp.next.prev = temp.prev.
*/

import java.util.*;
import java.io.*;

// HELPER DEFINITION:-
class Node {
    int data;
    Node next;
    Node prev;
    Node(int x) {
        data = x;
        next = null;
        prev = null;
    }
}

// CODE:-
class Solution {
    public Node deleteNode(Node head, int x) {
        if (head == null) return null;
        if (x == 1) {
            head = head.next;
            if (head != null) head.prev = null;
            return head;
        }

        Node temp = head;
        for (int i = 1; i < x && temp != null; i++) {
            temp = temp.next;
        }
        if (temp == null) return head;

        if (temp.prev != null) {
            temp.prev.next = temp.next;
        }
        if (temp.next != null) {
            temp.next.prev = temp.prev;
        }
        return head;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
