/*
QUESTION:
Merge `k` sorted linked-lists using a PriorityQueue (Min-Heap).

APPROACH:
- Insert the head node of each non-empty list into a Min-Heap.
- Create a dummy head for the result list.
- While the min-heap is non-empty:
  - Extract the node with minimum value.
  - Append it to result list.
  - If this node has a `next`, offer `node.next` into the min-heap.

TIME COMPLEXITY: O(N log K) where N is total nodes and K is number of lists.
SPACE COMPLEXITY: O(K) for min-heap holding at most K nodes at any time.
*/

import java.util.PriorityQueue;

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
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);

        for (ListNode head : lists) {
            if (head != null) pq.offer(head);
        }

        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        while (!pq.isEmpty()) {
            ListNode minNode = pq.poll();
            curr.next = minNode;
            curr = curr.next;
            if (minNode.next != null) {
                pq.offer(minNode.next);
            }
        }
        return dummy.next;
    }
}
