/*
QUESTION:- 
Construct the linked list from arr and return the head of the linked list.

Example 1:

Input:
n = 5
arr = [1,2,3,4,5]
Output:
1 2 3 4 5
Explanation: Linked list for the given array will be 1->2->3->4->5.
Example 2:

Input:
n = 2
arr = [2,4]
Output:
2 4
Explanation: Linked list for the given array will be 2->4.

APPROACH:- 
The approach to construct a linked list from an array is as follows:

1. Create the head node of the linked list using the first element of the array.
2. Initialize a current pointer to the head node.
3. Traverse the array starting from the second element.
4. For each element in the array, create a new node and assign the element as its data.
5. Set the `next` pointer of the current node to the newly created node.
6. Update the current pointer to the newly created node.
7. Repeat steps 4-6 until all elements in the array are processed.
8. Return the head node of the constructed linked list.

CODE:-
*/

import java.util.*;
import java.io.*;

// CODE:-
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
