/*
 * Problem Name: Add 1 to a number represented as Linked List
 * Platform: GeeksforGeeks
 * Difficulty: Medium
 * Time Complexity: O(N)
 * Space Complexity: O(N) due to recursion stack
 */

class Node {
    int data;
    Node next;
    Node(int x) { data = x; next = null; }
}

class Solution {
    public static Node addOne(Node head) {
        int carry = helper(head);
        if (carry > 0) {
            Node newHead = new Node(carry);
            newHead.next = head;
            return newHead;
        }
        return head;
    }

    private static int helper(Node node) {
        if (node == null) return 1;
        int sum = node.data + helper(node.next);
        node.data = sum % 10;
        return sum / 10;
    }
}
