/*
QUESTION:
Merge `k` sorted linked-lists using Divide and Conquer.

APPROACH:
- Merge lists pairwise in divide-and-conquer fashion (like Merge Sort).
- In each round, merge `lists[i]` and `lists[i + interval]`, doubling interval each round.
- Two sorted lists can be merged in O(L1 + L2).

TIME COMPLEXITY: O(N log K) where N is total nodes and K is number of lists.
SPACE COMPLEXITY: O(1) auxiliary space (merging in-place with existing pointers).
*/

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    private static ListNode mergeTwo(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        curr.next = (l1 != null) ? l1 : l2;
        return dummy.next;
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        int interval = 1;
        while (interval < lists.length) {
            for (int i = 0; i + interval < lists.length; i += interval * 2) {
                lists[i] = mergeTwo(lists[i], lists[i + interval]);
            }
            interval *= 2;
        }
        return lists[0];
    }
}
