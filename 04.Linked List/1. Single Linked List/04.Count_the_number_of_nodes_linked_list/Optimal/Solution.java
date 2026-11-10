/*
QUESTION:-
Given a singly linked list. The task is to find the length of the linked list, where length is defined as the number of nodes in the linked list.

Example:
Input: LinkedList: 1->2->3->4->5
Output: 5
*/

/*
APPROACH:-
Iterative Count: Traverse the linked list using a temp pointer, incrementing a counter until temp is null.
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
        int count = 0;
        Node temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
