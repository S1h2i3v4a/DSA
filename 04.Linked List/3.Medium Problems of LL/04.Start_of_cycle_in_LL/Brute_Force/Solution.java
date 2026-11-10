/*
QUESTION:-
Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.

Example:
Input: head = [3,2,0,-4], pos = 1
Output: tail connects to node index 1
*/

/*
APPROACH:-
Hash Set: Store each node reference in a HashSet. The first node encountered that is already in the set is the start of the cycle.
*/

import java.util.*;
import java.io.*;

// HELPER DEFINITION:-
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; next = null; }
}

// CODE:-
class Solution {
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> visited = new HashSet<>();
        ListNode curr = head;
        while (curr != null) {
            if (visited.contains(curr)) return curr;
            visited.add(curr);
            curr = curr.next;
        }
        return null;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(N)
