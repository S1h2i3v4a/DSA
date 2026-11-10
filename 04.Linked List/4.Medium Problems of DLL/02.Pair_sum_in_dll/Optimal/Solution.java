/*
QUESTION:-
Given a sorted doubly linked list of positive distinct elements, the task is to find pairs in the doubly linked list whose sum is equal to given value target.

Example:
Input: 1 <-> 2 <-> 4 <-> 5 <-> 6 <-> 8 <-> 9, target = 7
Output: (1, 6), (2, 5)
*/

/*
APPROACH:-
Two Pointers (Left and Right): Initialize left at head and right at tail. While left.data < right.data: if sum == target, record pair and move both pointers inward. If sum < target, advance left; else retreat right.
*/

import java.util.*;
import java.io.*;

// HELPER DEFINITION:-
class Node {
    int data;
    Node next;
    Node prev;
    Node(int val) {
        data = val;
        next = null;
        prev = null;
    }
}

// CODE:-
class Solution {
    public static ArrayList<ArrayList<Integer>> findPairsWithGivenSum(int target, Node head) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        if (head == null) return ans;

        Node left = head;
        Node right = head;
        while (right.next != null) {
            right = right.next;
        }

        while (left != null && right != null && left.data < right.data) {
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
