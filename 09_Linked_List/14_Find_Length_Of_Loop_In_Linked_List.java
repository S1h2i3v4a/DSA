/*
 * Problem Name: Find length of Loop in Linked List
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
    public int countNodesinLoop(Node head) {
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                int count = 1;
                Node temp = slow.next;
                while (temp != slow) {
                    count++;
                    temp = temp.next;
                }
                return count;
            }
        }
        return 0;
    }
}
