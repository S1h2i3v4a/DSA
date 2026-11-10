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
Traverse to end for each element: Create head from arr[0]. For each subsequent element arr[i], traverse from head to the end of the list and attach the new node.
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
        for (int i = 1; i < arr.length; i++) {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = new Node(arr[i]);
        }
        return head;
    }
}

// TIME COMPLEXITY = O(N^2)
// SPACE COMPLEXITY = O(1)
