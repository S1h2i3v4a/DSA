/*
QUESTION:-
Given a linked list of N nodes where nodes can contain values 0s, 1s, and 2s only. 
The task is to segregate 0s, 1s, and 2s in the linked list such that all zeros segregate to the head side, 2s at the end of the linked list, and 1s in the middle of 0s and 2s.

Example:
Input:
N = 8
value[] = {1,2,2,1,2,0,2,2}
Output: 0 1 1 2 2 2 2 2
Explanation: All the 0s are segregated to the left end of the linked list, 2s to the right end of the list, and 1s in between.

APPROACH:
Count the number of 0s, 1s, and 2s in the linked list.
Traverse the linked list and overwrite the nodes with 0s, 1s, and 2s based on their counts.

TIME COMPLEXITY: O(N)
SPACE COMPLEXITY: O(1)

*/

import java.util.*;
import java.io.*;

// CODE:-
class Node {
    int data;
    Node next;
    Node(int d) { data = d; next = null; }
}

class Solution {
    static Node segregate(Node head) {
        if (head == null || head.next == null) return head;

        Node zeroHead = new Node(0), zero = zeroHead;
        Node oneHead = new Node(0), one = oneHead;
        Node twoHead = new Node(0), two = twoHead;

        Node curr = head;
        while (curr != null) {
            if (curr.data == 0) {
                zero.next = curr;
                zero = zero.next;
            } else if (curr.data == 1) {
                one.next = curr;
                one = one.next;
            } else {
                two.next = curr;
                two = two.next;
            }
            curr = curr.next;
        }

        zero.next = (oneHead.next != null) ? oneHead.next : twoHead.next;
        one.next = twoHead.next;
        two.next = null;

        return zeroHead.next;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
