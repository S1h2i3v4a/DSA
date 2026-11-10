/*
QUESTION:-
A linked list of length n is given such that each node contains an additional random pointer, which could point to any node in the list, or null.
Construct a deep copy of the list.

Example:
Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
*/

/*
APPROACH:-
Hash Map Mapping: Use a HashMap<Node, Node> to map each original node to its copy. In first pass, create clone nodes. In second pass, wire clone.next = map.get(curr.next) and clone.random = map.get(curr.random).
*/

import java.util.*;
import java.io.*;

// HELPER DEFINITION:-
class Node {
    int val;
    Node next;
    Node random;
    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

// CODE:-
class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) return null;
        Map<Node, Node> map = new HashMap<>();

        Node curr = head;
        while (curr != null) {
            map.put(curr, new Node(curr.val));
            curr = curr.next;
        }

        curr = head;
        while (curr != null) {
            Node copy = map.get(curr);
            copy.next = map.get(curr.next);
            copy.random = map.get(curr.random);
            curr = curr.next;
        }
        return map.get(head);
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(N)
