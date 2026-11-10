/*
QUESTION:-
Given a doubly-linked list, a position p, and an integer x. The task is to add a new node with value x at the position just after p-th node of the doubly linked list (0-based indexing).

Example:
Input: LinkedList: 2<->4<->5, p = 2, x = 6
Output: 2<->4<->5<->6
*/

/*
APPROACH:-
Pointer rewiring: Traverse p steps from head to reach node at position p. Create new node, link newNode.next = temp.next, if temp.next != null then temp.next.prev = newNode, temp.next = newNode, and newNode.prev = temp.
*/

import java.util.*;
import java.io.*;

// HELPER DEFINITION:-
class Node {
    int data;
    Node next;
    Node prev;
    Node(int data) {
        this.data = data;
        next = null;
        prev = null;
    }
}

// CODE:-
class Solution {
    Node addNode(Node head, int p, int x) {
        Node temp = head;
        for (int i = 0; i < p && temp != null; i++) {
            temp = temp.next;
        }
        if (temp == null) return head;

        Node newNode = new Node(x);
        newNode.next = temp.next;
        newNode.prev = temp;
        if (temp.next != null) {
            temp.next.prev = newNode;
        }
        temp.next = newNode;
        return head;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
