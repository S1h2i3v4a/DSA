/*
QUESTION:-
Given a linked list of N nodes where nodes can contain values 0s, 1s, and 2s only. The task is to segregate 0s, 1s, and 2s linked list such that all zeros segregate to head side, 2s at the end, and 1s in the middle.

Example:
Input: 1 -> 2 -> 2 -> 1 -> 2 -> 0 -> 2 -> 2
Output: 0 -> 1 -> 1 -> 2 -> 2 -> 2 -> 2 -> 2
*/

/*
APPROACH:-
Three Dummy Heads (In-place Relinking): Create dummy heads zeroHead, oneHead, and twoHead. Traverse original list, distributing nodes to the 3 lists. Finally, stitch zeroList -> oneList -> twoList.
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
