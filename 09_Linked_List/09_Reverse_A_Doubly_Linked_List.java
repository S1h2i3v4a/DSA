/*
 * Problem Name: Reverse a Doubly Linked List
 * Platform: GeeksforGeeks / LeetCode
 * Difficulty: Easy
 * Time Complexity: O(N)
 * Space Complexity: O(1)
 */

class DLLNode {
    int data;
    DLLNode next;
    DLLNode prev;
    DLLNode(int val) {
        data = val;
        next = null;
        prev = null;
    }
}

class Solution {
    public DLLNode reverseDLL(DLLNode head) {
        if (head == null || head.next == null) return head;
        DLLNode current = head;
        DLLNode temp = null;

        while (current != null) {
            temp = current.prev;
            current.prev = current.next;
            current.next = temp;
            current = current.prev;
        }
        if (temp != null) {
            head = temp.prev;
        }
        return head;
    }
}
