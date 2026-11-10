/*
QUESTION:-
Geek is learning about Doubly Linked List. Given an integer array arr of size n. Construct the doubly linked list from arr and return the head of it.

Example:
Input: n = 5, arr = [1, 2, 3, 4, 5]
Output: 1 <-> 2 <-> 3 <-> 4 <-> 5
*/

/*
APPROACH:-
Append by traversing: Create head from arr[0]. For each subsequent element arr[i], traverse from head to the end of the DLL, set last.next = newNode and newNode.prev = last.
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
        for (int i = 1; i < arr.length; i++) {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            Node newNode = new Node(arr[i]);
            temp.next = newNode;
            newNode.prev = temp;
        }
        return head;
    }
}

// TIME COMPLEXITY = O(N^2)
// SPACE COMPLEXITY = O(1)
