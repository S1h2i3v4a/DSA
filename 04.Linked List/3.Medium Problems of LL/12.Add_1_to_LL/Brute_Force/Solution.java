/*
QUESTION:-
A number N is represented in Linked List such that each digit corresponds to a node in linked list. You need to add 1 to it.

Example:
Input: LinkedList: 4->5->6
Output: 4->5->7
*/

/*
APPROACH:-
Reverse, Add, Reverse: Reverse the linked list so least significant digit is first. Add 1 and propagate carry. Reverse the result back.
*/

import java.util.*;
import java.io.*;

// HELPER DEFINITION:-
class Node {
    int data;
    Node next;
    Node(int x) { data = x; next = null; }
}

// CODE:-
class Solution {
    public static Node addOne(Node head) {
        head = reverse(head);
        Node curr = head;
        int carry = 1;

        while (curr != null) {
            int sum = curr.data + carry;
            curr.data = sum % 10;
            carry = sum / 10;
            if (curr.next == null && carry > 0) {
                curr.next = new Node(carry);
                carry = 0;
                break;
            }
            curr = curr.next;
        }
        return reverse(head);
    }

    private static Node reverse(Node head) {
        Node prev = null, curr = head;
        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}

// TIME COMPLEXITY = O(3N) = O(N)
// SPACE COMPLEXITY = O(1)
