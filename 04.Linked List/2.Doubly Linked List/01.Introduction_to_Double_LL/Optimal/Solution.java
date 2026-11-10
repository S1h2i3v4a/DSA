/*
QUESTION:-
Geek is learning about Doubly Linked List. Given an integer array arr of size n. Construct the doubly linked list from arr and return the head of it.

Example:
Input: n = 5, arr = [1, 2, 3, 4, 5]
Output: 1 <-> 2 <-> 3 <-> 4 <-> 5
*/

/*
APPROACH:-
Maintain Prev Pointer: Keep a prev pointer pointing to the current tail. Create each new node, wire prev.next = newNode and newNode.prev = prev, then update prev = newNode.
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
    Node constructDLL(int arr[]) {
        if (arr == null || arr.length == 0) return null;
        Node head = new Node(arr[0]);
        Node prev = head;
        for (int i = 1; i < arr.length; i++) {
            Node temp = new Node(arr[i]);
            prev.next = temp;
            temp.prev = prev;
            prev = temp;
        }
        return head;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
