/*
QUESTION:-
Given a linked list of size N. Checks whether a given Linked List contains a loop or not and if the loop is present then return the count of nodes in a loop or else return 0.

Example:
Input: N = 4, 1->2->3->4 with loop connecting 4 to 2
Output: 3
*/

/*
APPROACH:-
Map with Visit Timer: Store each visited node and the step/timer when it was visited in a HashMap. When a node is revisited, loop size is current_timer - previous_timer.
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
        Map<Node, Integer> map = new HashMap<>();
        Node temp = head;
        int timer = 0;
        while (temp != null) {
            if (map.containsKey(temp)) {
                return timer - map.get(temp);
            }
            map.put(temp, timer++);
            temp = temp.next;
        }
        return 0;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(N)
