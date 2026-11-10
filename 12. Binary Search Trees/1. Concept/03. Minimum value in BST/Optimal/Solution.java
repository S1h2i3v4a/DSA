/*
QUESTION:
Given a Binary Search Tree (BST), find the minimum value in it.

APPROACH:
- In a BST, the leftmost node holds the minimum value.
- Follow left child pointers until `root.left == null`.
- Return `root.data`.

TIME COMPLEXITY: O(H) where H is tree height.
SPACE COMPLEXITY: O(1) constant auxiliary space.
*/

class Node {
    int data;
    Node left, right;
    Node(int val) { data = val; }
}

class Solution {
    int minValue(Node root) {
        if (root == null) return -1;
        while (root.left != null) {
            root = root.left;
        }
        return root.data;
    }
}
