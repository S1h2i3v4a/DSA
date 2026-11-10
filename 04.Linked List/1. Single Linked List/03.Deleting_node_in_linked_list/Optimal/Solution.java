/*
QUESTION:-
There is a singly-linked list head and we want to delete a node in it.
You are given the node to be deleted node. You will not be given access to the first node of head.
All the values of the linked list are unique, and it is guaranteed that the given node is not the last node in the linked list.

Example:
Input: head = [4,5,1,9], node = 5
Output: [4,1,9]
*/

/*
APPROACH:-
Copy Next Node's Value: Since we don't have access to the previous node, copy the value of the next node into the current node, then delete the next node by setting node.next = node.next.next.
*/

import java.util.*;
import java.io.*;

// HELPER DEFINITION:-
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

// CODE:-
class Solution {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}

// TIME COMPLEXITY = O(1)
// SPACE COMPLEXITY = O(1)
