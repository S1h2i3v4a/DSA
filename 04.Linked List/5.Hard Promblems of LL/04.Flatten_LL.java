/**QUESTION:**

Given a Linked List of size N, where every node represents a sub-linked-list and contains two pointers:
(i) a next pointer to the next node,
(ii) a bottom pointer to a linked list where this node is the head.
Each of the sub-linked lists is in sorted order.
Flatten the Link List such that all the nodes appear in a single level while maintaining the sorted order.

Note: The flattened list will be printed using the bottom pointer instead of the next pointer.

**APPROACH:**

To flatten the linked list, we can use a recursive approach. The idea is to flatten the list from right to left. Starting from the last node, we recursively flatten the sublist pointed by the bottom pointer and merge it with the current node. We continue this process until we reach the head of the original linked list. Finally, we return the flattened list.

**TIME COMPLEXITY:** The time complexity is O(N), where N is the total number of nodes in the linked list.

**SPACE COMPLEXITY:** The space complexity is O(1) since we are modifying the given linked list in-place without using any extra space.

**CODE:**/

import java.util.*;
import java.io.*;

// CODE:-
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

class Solution {
    Node mergeTwoLists(Node a, Node b) {
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
    Node flatten(Node root) {
        if (root == null || root.next == null) return root;
        root.next = flatten(root.next);
        root = mergeTwoLists(root, root.next);
        return root;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
