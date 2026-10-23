/*
Given a doubly linked list of n elements. The task is to reverse the doubly linked list.

Example 1:

Input:
LinkedList: 3 <--> 4 <--> 5
Output: 5 4 3
Example 2:

Input:
LinkedList: 75 <--> 122 <--> 59 <--> 196
Output: 196 59 122 75

APPROACH:-
1. The function `reverseDLL` takes in one parameter: `head`, which is a pointer to the head of the doubly linked list.
2. The variable `curr` is initialized with the `head` pointer, which represents the current node being processed.
3. The variable `ans` is initialized as `NULL`. It will store the new head of the reversed linked list.
4. The code enters a `while` loop, which continues until `curr` becomes `NULL`, indicating that all nodes have been processed.
5. Inside the loop, the variable `nxt` is assigned the value of `curr->next`, which represents the next node in the original list.
6. The next and previous pointers of the current node (`curr->next` and `curr->prev`) are swapped to reverse the links.
7. The `if` condition checks if `curr->prev` is `NULL`, indicating that `curr` is the last node in the original list (now the first node in the reversed list). In this case, the `ans` pointer is updated to store the new head of the reversed list.
8. The `curr` pointer is updated to `curr->prev`, which moves it to the previous node in the original list.
9. After the loop, the `ans` pointer now holds the new head of the reversed list. It is returned as the result.

CODE:-
*/

import java.util.*;
import java.io.*;

// CODE:-
class DLLNode {
    int data;
    DLLNode next;
    DLLNode prev;
    DLLNode(int val) {
        data = val;
        next = null;
        prev = null;
    }
}

class Solution {
    public DLLNode reverseDLL(DLLNode head) {
        if (head == null || head.next == null) return head;
        DLLNode current = head;
        DLLNode temp = null;

        while (current != null) {
            temp = current.prev;
            current.prev = current.next;
            current.next = temp;
            current = current.prev;
        }
        if (temp != null) {
            head = temp.prev;
        }
        return head;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
