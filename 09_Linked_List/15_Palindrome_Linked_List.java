/*
 * Problem Name: Palindrome Linked List
 * Platform: LeetCode (234) / GeeksforGeeks
 * Difficulty: Easy
 * Time Complexity: O(N)
 * Space Complexity: O(1)
 */

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; next = null; }
}

class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;

        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode secondHalf = reverseList(slow.next);
        ListNode firstHalf = head;
        ListNode temp = secondHalf;

        boolean isPal = true;
        while (temp != null) {
            if (firstHalf.val != temp.val) {
                isPal = false;
                break;
            }
            firstHalf = firstHalf.next;
            temp = temp.next;
        }

        slow.next = reverseList(secondHalf);
        return isPal;
    }

    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}
