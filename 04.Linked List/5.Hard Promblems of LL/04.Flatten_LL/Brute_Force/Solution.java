/*
QUESTION:-
Given a Linked List of size N, where every node represents a sub-linked-list and contains two pointers:
(i) a next pointer to the next node,
(ii) a bottom pointer to a linked list where this node is head.
Each sub-linked-list is in sorted order. Flatten the list into a single sorted linked list using bottom pointers.

Example:
Input:
5 -> 10 -> 19 -> 28
|    |     |     |
7    20    22    35
|          |     |
8          50    40
Output: 5->7->8->10->19->20->22->28->35->40->50
*/

/*
APPROACH:-
Collect, Sort, Reconstruct: Traverse entire multi-level list and collect all node values into an ArrayList. Sort the list, then create a new single-level list connected via bottom pointers.
*/

import java.util.*;
import java.io.*;

// HELPER DEFINITION:-
class Node {
    int data;
    Node next;
    Node bottom;
    Node(int d) {
        data = d;
        next = null;
        bottom = null;
    }
}

// CODE:-
class Solution {
    Node flatten(Node root) {
        if (root == null) return null;
        List<Integer> list = new ArrayList<>();
        Node curr = root;
        while (curr != null) {
            Node bCurr = curr;
            while (bCurr != null) {
                list.add(bCurr.data);
                bCurr = bCurr.bottom;
            }
            curr = curr.next;
        }
        Collections.sort(list);

        Node dummy = new Node(0);
        Node temp = dummy;
        for (int val : list) {
            temp.bottom = new Node(val);
            temp = temp.bottom;
        }
        return dummy.bottom;
    }
}

// TIME COMPLEXITY = O(N * M log(N * M))
// SPACE COMPLEXITY = O(N * M)
