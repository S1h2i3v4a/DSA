/*Given a doubly linked list and a position. The task is to delete a node from given position in a doubly linked list.

Example 1:

Input:
LinkedList = 1 <--> 3 <--> 4 
x = 3
Output: 1 3  
Explanation: After deleting the node at
position 3 (position starts from 1),
the linked list will be now as 1->3.
Example 2:

Input:
LinkedList = 1 <--> 5 <--> 2 <--> 9  
x = 1
Output: 5 2 9

APPROACH:-
1. The function `deleteNode` takes in two parameters: `head_ref`, which is a pointer to the head of the doubly linked list, and `x`, which represents the position of the node to be deleted.
2. The variable `temp` is initialized as the `head_ref`, which is a pointer to the head node of the linked list.
3. If `x` is equal to 1, it means the node to be deleted is the head node. In this case, the `temp` pointer is moved to the next node, and the new `temp` pointer is returned as the new head of the linked list. This effectively deletes the original head node.
4. If `x` is greater than 1, the code enters a loop that iterates `x-1` times. This loop moves the `temp` pointer to the node just before the position to be deleted.
5. After the loop, the variable `previous` is assigned the value of `temp`, which represents the node just before the position to be deleted.
6. The `previous->next` pointer is adjusted to skip over the node to be deleted by assigning it the value of `temp->next->next`.
7. The `temp->prev` pointer is adjusted to point to the `previous` node, updating the backward link.
8. Finally, the updated `head_ref` is returned.

CODE:-
*/

import java.util.*;
import java.io.*;

// CODE:-
class Node {
    int data;
    Node next;
    Node prev;
    Node(int x) {
        data = x;
        next = null;
        prev = null;
    }
}

class Solution {
    public Node deleteNode(Node head, int x) {
        if (head == null) return null;
        if (x == 1) {
            head = head.next;
            if (head != null) head.prev = null;
            return head;
        }

        Node temp = head;
        for (int i = 1; i < x && temp != null; i++) {
            temp = temp.next;
        }
        if (temp == null) return head;

        if (temp.next != null) {
            temp.next.prev = temp.prev;
        }
        if (temp.prev != null) {
            temp.prev.next = temp.next;
        }
        return head;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
