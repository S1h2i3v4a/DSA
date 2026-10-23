/*
Given a sorted doubly linked list of positive distinct elements, the task is to find pairs in the doubly linked list whose sum is equal to a given value `target`.

Example 1:
Input:  
1 <-> 2 <-> 4 <-> 5 <-> 6 <-> 8 <-> 9
Target: 7
Output: (1, 6), (2, 5)
Explanation: We can see that there are two pairs (1, 6) and (2, 5) with a sum of 7.

Example 2:
Input: 
1 <-> 5 <-> 6
Target: 6
Output: (1, 5)
Explanation: We can see that there is one pair (1, 5) with a sum of 6.

Approach:
1. Initialize two pointers, `start` and `end`, pointing to the beginning and end of the doubly linked list, respectively.
2. While the `start` pointer's data is less than the `end` pointer's data:
   - Calculate the sum of the data at the `start` and `end` pointers.
   - If the sum is equal to the target value, add the pair (start->data, end->data) to the result vector and move both the `start` and `end` pointers.
   - If the sum is less than the target value, move the `start` pointer to the next node.
   - If the sum is greater than the target value, move the `end` pointer to the previous node.
3. Return the result vector containing all the pairs whose sum is equal to the target.

Time Complexity: O(N), where N is the number of nodes in the doubly linked list.
Space Complexity: O(1).

CODE:
*/

import java.util.*;
import java.io.*;

// CODE:-
class Node {
    int data;
    Node next, prev;
    Node(int val) {
        data = val;
        next = null;
        prev = null;
    }
}

class Solution {
    public static ArrayList<ArrayList<Integer>> findPairsWithGivenSum(int target, Node head) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        if (head == null) return ans;
        Node left = head;
        Node right = head;
        while (right.next != null) {
            right = right.next;
        }
        while (left != null && right != null && left != right && right.next != left) {
            int sum = left.data + right.data;
            if (sum == target) {
                ArrayList<Integer> pair = new ArrayList<>();
                pair.add(left.data);
                pair.add(right.data);
                ans.add(pair);
                left = left.next;
                right = right.prev;
            } else if (sum < target) {
                left = left.next;
            } else {
                right = right.prev;
            }
        }
        return ans;
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
