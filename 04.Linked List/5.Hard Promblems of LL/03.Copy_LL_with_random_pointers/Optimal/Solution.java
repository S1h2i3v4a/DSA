/*
QUESTION:-
A linked list of length n is given such that each node contains an additional random pointer, which could point to any node in the list, or null.
Construct a deep copy of the list.

Example:
Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
*/

/*
APPROACH:-
Three-pass Interweaving without Map: 1) Insert copy nodes right after original nodes: A -> A' -> B -> B'. 2) Assign random pointers: curr.next.random = (curr.random != null) ? curr.random.next : null. 3) Separate original and copied lists.
*/

import java.util.*;
import java.io.*;

// HELPER DEFINITION:-
class Node {
    int val;
    Node next;
    Node random;
    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

// CODE:-
class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) return null;

        // Step 1: Insert copy nodes
        Node curr = head;
        while (curr != null) {
            Node copy = new Node(curr.val);
            copy.next = curr.next;
            curr.next = copy;
            curr = copy.next;
        }

        // Step 2: Assign random pointers
        curr = head;
        while (curr != null) {
            if (curr.random != null) {
                curr.next.random = curr.random.next;
            }
            curr = curr.next.next;
        }

        // Step 3: Separate lists
        Node dummy = new Node(0);
        Node copyCurr = dummy;
        curr = head;
        while (curr != null) {
            copyCurr.next = curr.next;
            curr.next = curr.next.next;
            copyCurr = copyCurr.next;
            curr = curr.next;
        }

        return dummy.next;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
