/*
 * Problem Name: Segregate even and odd nodes in a Linked List
 * Platform: LeetCode (328) / GeeksforGeeks
 * Difficulty: Medium
 * Time Complexity: O(N)
 * Space Complexity: O(1)
 */

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; next = null; }
}

class Solution {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;

        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }

        odd.next = evenHead;
        return head;
    }
}
