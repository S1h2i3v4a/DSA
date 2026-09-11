/*
 * Problem Name: Introduction to Doubly Linked List, Construct DLL
 * Platform: GeeksforGeeks
 * Difficulty: Easy
 * Time Complexity: O(N)
 * Space Complexity: O(N)
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
    Node constructDLL(int arr[]) {
        if (arr == null || arr.length == 0) return null;
        Node head = new Node(arr[0]);
        Node prevNode = head;

        for (int i = 1; i < arr.length; i++) {
            Node temp = new Node(arr[i]);
            prevNode.next = temp;
            temp.prev = prevNode;
            prevNode = temp;
        }
        return head;
    }
}
