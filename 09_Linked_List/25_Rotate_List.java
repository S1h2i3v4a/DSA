/*
 * Problem Name: Rotate List
 * Platform: LeetCode (61) / GeeksforGeeks
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
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) return head;

        ListNode tail = head;
        int length = 1;
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }

        tail.next = head;
        k = k % length;
        int stepsToNewHead = length - k;
        ListNode newTail = tail;

        while (stepsToNewHead > 0) {
            newTail = newTail.next;
            stepsToNewHead--;
        }

        ListNode newHead = newTail.next;
        newTail.next = null;

        return newHead;
    }
}
