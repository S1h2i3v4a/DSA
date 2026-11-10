/*
QUESTION:-
Given an array of integer arr. Your task is to construct the linked list from arr & return the head of the linked list.

Example:
Input: arr = [1, 2, 3, 4, 5]
Output: 1->2->3->4->5
Explanation: Linked list for the given array will be 1->2->3->4->5.
*/

/*
APPROACH:-
Maintain Tail Pointer: Initialize head with arr[0] and keep a mover/tail pointer pointing to the current last node. For each subsequent element, append directly at mover.next and advance mover in O(1).
*/

import java.util.*;
import java.io.*;

// HELPER DEFINITION:-
class Node {
    int data;
    Node next;
    Node() { data = 0; }
    Node(int d) { data = d; next = null; }
}

// CODE:-
class Solution {
    static Node constructLL(int arr[]) {
        if (arr == null || arr.length == 0) return null;
        Node head = new Node(arr[0]);
        Node mover = head;
        for (int i = 1; i < arr.length; i++) {
            mover.next = new Node(arr[i]);
            mover = mover.next;
        }
        return head;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
