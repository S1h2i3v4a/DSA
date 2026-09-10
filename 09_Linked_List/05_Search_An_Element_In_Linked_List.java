/*
 * Problem Name: Search an Element in Linked List
 * Platform: GeeksforGeeks
 * Difficulty: Easy
 * Time Complexity: O(N)
 * Space Complexity: O(1)
 */

class Node {
    int data;
    Node next;
    Node(int d) { data = d; next = null; }
}

class Solution {
    static boolean searchKey(int n, Node head, int key) {
        Node temp = head;
        while (temp != null) {
            if (temp.data == key) return true;
            temp = temp.next;
        }
        return false;
    }
}
