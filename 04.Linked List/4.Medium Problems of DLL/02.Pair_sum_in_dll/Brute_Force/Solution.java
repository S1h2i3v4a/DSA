/*
QUESTION:-
Given a sorted doubly linked list of positive distinct elements, the task is to find pairs in the doubly linked list whose sum is equal to given value target.

Example:
Input: 1 <-> 2 <-> 4 <-> 5 <-> 6 <-> 8 <-> 9, target = 7
Output: (1, 6), (2, 5)
*/

/*
APPROACH:-
Nested Loops: Check all pairs (t1, t2) with nested while loops. If t1.data + t2.data == target, add pair to result.
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
        Node t1 = head;
        while (t1 != null) {
            Node t2 = t1.next;
            while (t2 != null && t1.data + t2.data <= target) {
                if (t1.data + t2.data == target) {
                    ArrayList<Integer> pair = new ArrayList<>();
                    pair.add(t1.data);
                    pair.add(t2.data);
                    ans.add(pair);
                }
                t2 = t2.next;
            }
            t1 = t1.next;
        }
        return ans;
    }
}

// TIME COMPLEXITY = O(N^2)
// SPACE COMPLEXITY = O(1)
