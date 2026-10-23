/*
QUESTION:-
A number N is represented in a linked list such that each digit corresponds to a node in the linked list. 
You need to add 1 to the number represented by the linked list.

APPROACH:
To add 1 to the number represented by the linked list, we can reverse the linked list, perform the addition, and then reverse it back.
First, reverse the linked list.
Traverse the reversed linked list while keeping track of the carry.
Add 1 to the least significant digit (head) and update the carry if necessary.
Continue traversing the linked list, adding the carry to each digit and updating the carry.
If there is still a remaining carry after traversing the linked list, create a new node with the carry as its value and append it to the end of the linked list.
Reverse the linked list again to restore its original order.

TIME COMPLEXITY: O(N), where N is the length of the linked list.
SPACE COMPLEXITY: O(1)

*/

import java.util.*;
import java.io.*;

// CODE:-
class Node {
    int data;
    Node next;
    Node(int x) { data = x; next = null; }
}

class Solution {
    public static Node addOne(Node head) {
        int carry = helper(head);
        if (carry > 0) {
            Node newHead = new Node(carry);
            newHead.next = head;
            return newHead;
        }
        return head;
    }

    private static int helper(Node node) {
        if (node == null) return 1;
        int sum = node.data + helper(node.next);
        node.data = sum % 10;
        return sum / 10;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
