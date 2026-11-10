/*
QUESTION:-
Given a linked list of n nodes and a key, the task is to check if the key is present in the linked list or not.

Example:
Input: n = 4, 1->2->3->4, key = 3
Output: true
*/

/*
APPROACH:-
Recursive Search: If head is null, return false. If head.data == key, return true. Otherwise recursively search in head.next.
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
        if (head == null) return false;
        if (head.data == key) return true;
        return searchKey(n, head.next, key);
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(N) (recursion stack)
