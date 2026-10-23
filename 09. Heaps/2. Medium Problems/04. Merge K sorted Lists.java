/*
QUESTION:
You are given an array of k linked-lists lists, each linked-list is sorted in ascending order. Merge all the linked-lists into one sorted linked-list and return it.

APPROACH:
To merge K sorted linked lists, we can use a min-heap (priority queue) to store the smallest nodes from each list.
1. Create a min-heap of size K to store the current smallest nodes from each list.
2. Initialize the min-heap with the head node from each list.
3. While the min-heap is not empty, extract the smallest node from the min-heap, add it to the merged linked list, and replace it with the next node from the same list.
4. Repeat step 3 until all nodes from all lists are processed.

The mergeKLists function implements this approach. It uses a min-heap (priority queue) to merge K sorted linked lists into one sorted linked list.

TIME COMPLEXITY:
The time complexity of the mergeKLists function is O(N log K), where N is the total number of nodes across all lists and K is the number of linked lists. Inserting nodes into the min-heap and extracting the minimum node take O(log K) time, and we do this for N nodes. The overall time complexity is dominated by the heap operations.

SPACE COMPLEXITY:
The space complexity is O(K) as we need to store K nodes in the min-heap.
*/

import java.util.*;
import java.io.*;

// CODE:-
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
