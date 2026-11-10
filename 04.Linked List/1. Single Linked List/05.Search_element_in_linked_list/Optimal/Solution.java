/*
QUESTION:-
Given a linked list of n nodes and a key, the task is to check if the key is present in the linked list or not.

Example:
Input: n = 4, 1->2->3->4, key = 3
Output: true
*/

/*
APPROACH:-
Iterative Search: Traverse the list iteratively using a pointer temp. If temp.data == key, return true. If loop finishes without finding key, return false.
*/

import java.util.*;
import java.io.*;

// HELPER DEFINITION:-
class Node {
    int data;
    Node next;
    Node(int d) { data = d; next = null; }
}

// CODE:-
class Solution {
    static boolean searchKey(int n, Node head, int key) {
        Node temp = head;
        while (temp != null) {
            if (temp.data == key) return true;
            temp = temp.next;
        }
        return false;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
