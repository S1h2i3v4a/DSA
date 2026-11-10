/*
QUESTION:
You are given an array of `k` linked-lists `lists`, each linked-list is sorted in ascending order.
Merge all the linked-lists into one sorted linked-list and return it.

APPROACH:
- Traverse all linked lists and collect all values into an `ArrayList`.
- Sort the list.
- Construct a new linked list from the sorted values.

TIME COMPLEXITY: O(N log N) where N is the total number of nodes across all lists.
SPACE COMPLEXITY: O(N) to store values and create new list nodes.
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        List<Integer> values = new ArrayList<>();

        for (ListNode node : lists) {
            while (node != null) {
                values.add(node.val);
                node = node.next;
            }
        }

        Collections.sort(values);
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int v : values) {
            curr.next = new ListNode(v);
            curr = curr.next;
        }
        return dummy.next;
    }
}
