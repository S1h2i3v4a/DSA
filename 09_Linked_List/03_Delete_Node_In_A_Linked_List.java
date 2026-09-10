/*
 * Problem Name: Delete Node in a Linked List
 * Platform: LeetCode (237) / GeeksforGeeks
 * Difficulty: Medium
 * Time Complexity: O(1)
 * Space Complexity: O(1)
 */

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

class Solution {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
