/*
QUESTION:-
Given a doubly linked list and an integer x. The task is to delete all occurrences of x from the doubly linked list.

Example:
Input: 2 <-> 2 <-> 10 <-> 8 <-> 4 <-> 2 <-> 5 <-> 2, x = 2
Output: 10 <-> 8 <-> 4 <-> 5
*/

/*
APPROACH:-
Single Pass Traversal: Traverse the DLL with pointer temp. When temp.data == x, adjust temp.prev.next and temp.next.prev. Update head if temp is head.
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
    static Node deleteAllOccurOfX(Node head, int x) {
        Node temp = head;
        while (temp != null) {
            if (temp.data == x) {
                if (temp == head) {
                    head = head.next;
                }
                Node nextNode = temp.next;
                Node prevNode = temp.prev;
                if (nextNode != null) nextNode.prev = prevNode;
                if (prevNode != null) prevNode.next = nextNode;
                temp = nextNode;
            } else {
                temp = temp.next;
            }
        }
        return head;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
