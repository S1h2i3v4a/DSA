/*
 * Problem Name: Sort a LL of 0's 1's and 2's
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
    static Node segregate(Node head) {
        if (head == null || head.next == null) return head;

        Node zeroHead = new Node(0), zero = zeroHead;
        Node oneHead = new Node(0), one = oneHead;
        Node twoHead = new Node(0), two = twoHead;

        Node curr = head;
        while (curr != null) {
            if (curr.data == 0) {
                zero.next = curr;
                zero = zero.next;
            } else if (curr.data == 1) {
                one.next = curr;
                one = one.next;
            } else {
                two.next = curr;
                two = two.next;
            }
            curr = curr.next;
        }

        zero.next = (oneHead.next != null) ? oneHead.next : twoHead.next;
        one.next = twoHead.next;
        two.next = null;

        return zeroHead.next;
    }
}
