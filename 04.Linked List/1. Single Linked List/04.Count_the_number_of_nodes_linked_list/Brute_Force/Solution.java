/*
QUESTION:-
Given a singly linked list. The task is to find the length of the linked list, where length is defined as the number of nodes in the linked list.

Example:
Input: LinkedList: 1->2->3->4->5
Output: 5
*/

/*
APPROACH:-
Recursive Count: Base case: if head == null return 0. Otherwise return 1 + getCount(head.next).
*/

import java.util.*;
import java.io.*;

// HELPER DEFINITION:-
class Node {
    int data;
    Node next;
    Node(int a) { data = a; next = null; }
}

// CODE:-
class Solution {
    public int getCount(Node head) {
        if (head == null) return 0;
        return 1 + getCount(head.next);
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(N) (recursion stack)
