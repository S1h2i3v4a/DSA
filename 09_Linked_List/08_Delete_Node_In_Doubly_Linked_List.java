/*
 * Problem Name: Delete Node in Doubly Linked List
 * Platform: GeeksforGeeks
 * Difficulty: Easy
 * Time Complexity: O(N)
 * Space Complexity: O(1)
 */

class Node {
    int data;
    Node next;
    Node prev;
    Node(int x) {
        data = x;
        next = null;
        prev = null;
    }
}

class Solution {
    public Node deleteNode(Node head, int x) {
        if (head == null) return null;
        if (x == 1) {
            head = head.next;
            if (head != null) head.prev = null;
            return head;
        }

        Node temp = head;
        for (int i = 1; i < x && temp != null; i++) {
            temp = temp.next;
        }
        if (temp == null) return head;

        if (temp.next != null) {
            temp.next.prev = temp.prev;
        }
        if (temp.prev != null) {
            temp.prev.next = temp.next;
        }
        return head;
    }
}
