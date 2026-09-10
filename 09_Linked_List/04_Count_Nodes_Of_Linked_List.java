/*
 * Problem Name: Count Nodes of Linked List
 * Platform: GeeksforGeeks
 * Difficulty: Basic
 * Time Complexity: O(N)
 * Space Complexity: O(1)
 */

class Node {
    int data;
    Node next;
    Node(int a) { data = a; next = null; }
}

class Solution {
    public int getCount(Node head) {
        int count = 0;
        Node temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }
}
