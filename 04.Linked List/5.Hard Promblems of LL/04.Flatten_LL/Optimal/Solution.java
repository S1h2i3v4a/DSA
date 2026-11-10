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
Recursive Merge of Sublists: Recursively flatten the rest of the list (root.next). Then merge the current vertical list (root) with the already flattened rest using bottom pointers in O(N*M).
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
        if (root == null || root.next == null) return root;
        root.next = flatten(root.next);
        root = mergeTwoLists(root, root.next);
        return root;
    }

    private Node mergeTwoLists(Node a, Node b) {
        Node temp = new Node(0);
        Node res = temp;
        while (a != null && b != null) {
            if (a.data < b.data) {
                temp.bottom = a;
                temp = temp.bottom;
                a = a.bottom;
            } else {
                temp.bottom = b;
                temp = temp.bottom;
                b = b.bottom;
            }
        }
        if (a != null) temp.bottom = a;
        else temp.bottom = b;
        return res.bottom;
    }
}

// TIME COMPLEXITY = O(N * M)
// SPACE COMPLEXITY = O(1) auxiliary (O(N) recursion stack)
