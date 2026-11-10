/*
QUESTION:-
Given a sorted doubly linked list of distinct/non-distinct elements. The task is to remove duplicates from the given list.

Example:
Input: 1 <-> 1 <-> 1 <-> 2 <-> 3 <-> 4
Output: 1 <-> 2 <-> 3 <-> 4
*/

/*
APPROACH:-
Hash Set: Store seen node values in a HashSet. If current node value was already seen, unlink the node by adjusting prev and next pointers.
*/

import java.util.*;
import java.io.*;

// HELPER DEFINITION:-
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

// CODE:-
class Solution {
    Node removeDuplicates(Node head) {
        Set<Integer> seen = new HashSet<>();
        Node temp = head;
        while (temp != null) {
            if (seen.contains(temp.data)) {
                Node nextNode = temp.next;
                if (temp.prev != null) temp.prev.next = temp.next;
                if (temp.next != null) temp.next.prev = temp.prev;
                temp = nextNode;
            } else {
                seen.add(temp.data);
                temp = temp.next;
            }
        }
        return head;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(N)
