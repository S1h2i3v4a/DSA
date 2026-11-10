/*
QUESTION:-
Given the head of a Singly Linked List and a value x, insert that value x at the end of the LinkedList and return the modified Linked List.

Example:
Input: LinkedList: 1->2->3->4->5, x = 6
Output: 1->2->3->4->5->6
*/

/*
APPROACH:-
Iterative Traversal: If head is null, return new Node(x). Otherwise traverse to the last node using a temp pointer, then set temp.next = new Node(x).
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
    Node insertAtEnd(Node head, int x) {
        if (head == null) return new Node(x);
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = new Node(x);
        return head;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
