/*
Question:
You are given an array nodes. It contains 7 integers, which represents the value of nodes of the binary tree in level order traversal. You are also given a root of the tree which has a value equal to nodes[0].
Your task to construct a binary tree by creating nodes for the remaining 6 nodes.

Approach:
- We can solve this problem recursively by performing a level-order traversal of the tree.
- Starting from the root node, we can recursively create the left and right child nodes using the given array of values.
- The position of each child node in the array can be calculated based on the index of its parent node.

Complexity Analysis:
- Since we are visiting each node once, the time complexity of this approach is O(N), where N is the number of nodes in the binary tree.
- The space complexity is O(N) as we are using the call stack for recursion.

Code:
*/

import java.io.*;
import java.util.*;

// CODE:-
class Node {
    int data;
    Node left, right;
    Node(int key) {
        data = key;
        left = right = null;
    }
}

class Solution {
    public static void create_tree(Node root0, ArrayList<Integer> v) {
        root0.data = v.get(0);
        root0.left = new Node(v.get(1));
        root0.right = new Node(v.get(2));
        root0.left.left = new Node(v.get(3));
        root0.left.right = new Node(v.get(4));
        root0.right.left = new Node(v.get(5));
        root0.right.right = new Node(v.get(6));
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
