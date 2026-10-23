/**QUESTION:**

You are given the head of a doubly Linked List and a key. Your task is to delete all occurrences of the given key and return the new doubly Linked List.

**Example:**

Input: 
2 <-> 2 <-> 10 <-> 8 <-> 4 <-> 2 <-> 5 <-> 2
Key: 2
Output: 
10 <-> 8 <-> 4 <-> 5
Explanation: 
All occurrences of 2 have been deleted.

**APPROACH:**

To delete all occurrences of the given key from a doubly linked list, we can traverse the list and check each node's data. If the data matches the key, we handle three cases: deleting the first node, deleting the last node, and deleting a middle node. We update the pointers accordingly to remove the node from the list.

**TIME COMPLEXITY:** The time complexity is O(N), where N is the number of nodes in the doubly linked list.

**SPACE COMPLEXITY:** The space complexity is O(1) since we are modifying the given linked list in-place without using any extra space.

**CODE:**/

import java.util.*;
import java.io.*;

// CODE:-
class Node {
    int data;
    Node next;
    Node prev;
    Node(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

class Solution {
    static Node deleteAllOccurOfX(Node head, int x) {
        Node temp = head;
        while (temp != null) {
            if (temp.data == x) {
                if (temp == head) {
                    head = temp.next;
                    if (head != null) head.prev = null;
                } else {
                    Node nextNode = temp.next;
                    Node prevNode = temp.prev;
                    if (nextNode != null) nextNode.prev = prevNode;
                    if (prevNode != null) prevNode.next = nextNode;
                }
            }
            temp = temp.next;
        }
        return head;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
