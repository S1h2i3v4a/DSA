/*
QUESTION:-
Given a linked list of N nodes where nodes can contain values 0s, 1s, and 2s only. The task is to segregate 0s, 1s, and 2s linked list such that all zeros segregate to head side, 2s at the end, and 1s in the middle.

Example:
Input: 1 -> 2 -> 2 -> 1 -> 2 -> 0 -> 2 -> 2
Output: 0 -> 1 -> 1 -> 2 -> 2 -> 2 -> 2 -> 2
*/

/*
APPROACH:-
Count & Overwrite: Traverse the list to count occurrences of 0, 1, and 2. In a second pass, overwrite node data with 0s, then 1s, then 2s.
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
    static Node segregate(Node head) {
        int c0 = 0, c1 = 0, c2 = 0;
        Node temp = head;
        while (temp != null) {
            if (temp.data == 0) c0++;
            else if (temp.data == 1) c1++;
            else c2++;
            temp = temp.next;
        }
        temp = head;
        while (temp != null) {
            if (c0 > 0) { temp.data = 0; c0--; }
            else if (c1 > 0) { temp.data = 1; c1--; }
            else { temp.data = 2; c2--; }
            temp = temp.next;
        }
        return head;
    }
}

// TIME COMPLEXITY = O(2N) = O(N)
// SPACE COMPLEXITY = O(1)
