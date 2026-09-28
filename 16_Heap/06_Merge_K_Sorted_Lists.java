/**
 * Problem Name: Merge K Sorted Lists
 * Platform: LeetCode (23)
 * Difficulty: Hard
 * 
 * Time Complexity: O(N log K) where N is total nodes and K is number of lists.
 * Space Complexity: O(K) for PriorityQueue.
 * 
 * Approach:
 * Min-Heap of List Heads.
 * 1. Maintain a Min PriorityQueue storing `ListNode` pointers ordered by `node.val`.
 * 2. Insert initial non-null head of all `K` lists into minHeap.
 * 3. Maintain dummy `head` and `tail` pointers.
 * 4. While minHeap is not empty:
 *    - Poll `smallestNode = minHeap.poll()`.
 *    - Attach `smallestNode` to `tail.next` and update `tail`.
 *    - If `smallestNode.next != null`, offer `smallestNode.next` to minHeap.
 */

import java.util.PriorityQueue;

class Merge_K_Sorted_Lists {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);

        for (ListNode node : lists) {
            if (node != null) {
                minHeap.offer(node);
            }
        }

        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;

        while (!minHeap.isEmpty()) {
            ListNode smallest = minHeap.poll();
            tail.next = smallest;
            tail = tail.next;

            if (smallest.next != null) {
                minHeap.offer(smallest.next);
            }
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(5);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        ListNode l3 = new ListNode(2);
        l3.next = new ListNode(6);

        ListNode[] lists = {l1, l2, l3};
        ListNode merged = mergeKLists(lists);

        System.out.print("Merged Sorted K Lists: ");
        while (merged != null) {
            System.out.print(merged.val + " -> ");
            merged = merged.next;
        }
        System.out.println("null");
    }
}
