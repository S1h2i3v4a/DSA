/*
QUESTION:-
Given a sorted doubly linked list of distinct/non-distinct elements. The task is to remove duplicates from the given list.

Example:
Input: 1 <-> 1 <-> 1 <-> 2 <-> 3 <-> 4
Output: 1 <-> 2 <-> 3 <-> 4
*/

/*
APPROACH:-
In-place duplicate skipping: Since the DLL is sorted, all duplicate elements appear consecutively. For each node, skip all subsequent nodes with the same value and wire next/prev pointers directly.
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
    Node removeDuplicates(Node head) {
        Node temp = head;
        while (temp != null && temp.next != null) {
            Node nextNode = temp.next;
            while (nextNode != null && nextNode.data == temp.data) {
                nextNode = nextNode.next;
            }
            temp.next = nextNode;
            if (nextNode != null) nextNode.prev = temp;
            temp = temp.next;
        }
        return head;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
