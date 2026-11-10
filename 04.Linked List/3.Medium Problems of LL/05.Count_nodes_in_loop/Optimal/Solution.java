/*
QUESTION:-
Given a linked list of size N. Checks whether a given Linked List contains a loop or not and if the loop is present then return the count of nodes in a loop or else return 0.

Example:
Input: N = 4, 1->2->3->4 with loop connecting 4 to 2
Output: 3
*/

/*
APPROACH:-
Floyd's Cycle-Finding: Find meeting point of slow and fast. Once met, fix fast and advance slow one step at a time, counting nodes until slow reaches fast again.
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
    public int countNodesinLoop(Node head) {
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                int count = 1;
                slow = slow.next;
                while (slow != fast) {
                    count++;
                    slow = slow.next;
                }
                return count;
            }
        }
        return 0;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
