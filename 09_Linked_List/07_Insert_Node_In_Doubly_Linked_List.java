/*
 * Problem Name: Insert a node in Doubly Linked List
 * Platform: GeeksforGeeks
 * Difficulty: Easy
 * Time Complexity: O(N)
 * Space Complexity: O(1)
 */

class Node {
    int data;
    Node next;
    Node prev;
    Node(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

class Solution {
    Node addNode(Node head, int pos, int data) {
        Node newNode = new Node(data);
        Node temp = head;
        for (int i = 0; i < pos && temp != null; i++) {
            temp = temp.next;
        }
        if (temp == null) return head;

        newNode.next = temp.next;
        newNode.prev = temp;
        if (temp.next != null) {
            temp.next.prev = newNode;
        }
        temp.next = newNode;
        return head;
    }
}
