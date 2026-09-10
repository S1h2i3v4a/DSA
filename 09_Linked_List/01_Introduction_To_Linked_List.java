/*
 * Problem Name: Introduction to Linked List, Construct LL
 * Platform: GeeksforGeeks
 * Difficulty: Easy
 * Time Complexity: O(N)
 * Space Complexity: O(N)
 */

class Node {
    int data;
    Node next;
    Node(int d) { data = d; next = null; }
}

class Solution {
    static Node constructLL(int arr[]) {
        if (arr == null || arr.length == 0) return null;
        Node head = new Node(arr[0]);
        Node current = head;
        for (int i = 1; i < arr.length; i++) {
            current.next = new Node(arr[i]);
            current = current.next;
        }
        return head;
    }
}
