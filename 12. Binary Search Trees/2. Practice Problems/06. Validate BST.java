/*
Question:
Given the root of a binary tree, determine if it is a valid binary search tree (BST).

A valid BST is defined as follows:
- The left subtree of a node contains only nodes with keys less than the node's key.
- The right subtree of a node contains only nodes with keys greater than the node's key.
- Both the left and right subtrees must also be binary search trees.

Example:
Input: root = [5,1,4,null,null,3,6]
Output: false
Explanation: The root node's value is 5 but its right child's value is 4.

Approach:
- Initialize the range with LONG_MIN and LONG_MAX values.
- Now, if a node->val is out of the range then it's not a BST
- And, then check for the left and right subtrees with modified range 

Time Complexity: O(n)
- The in-order traversal visits each node exactly once, where n is the number of nodes in the binary tree.

Space Complexity: O(h)
- The space complexity is determined by the recursion stack during the in-order traversal, where h is the height of the binary tree.
- In the worst case, the height of the binary tree can be n for a skewed tree, so the space complexity becomes O(n).
- However, for a balanced BST, the height is log n, so the space complexity becomes O(log n).

*/

import java.io.*;
import java.util.*;


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

// CODE:-
class Validate_Binary_Search_Tree {
    public boolean isValidBST(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean validate(TreeNode node, long minVal, long maxVal) {
        if (node == null) return true;

        if (node.val <= minVal || node.val >= maxVal) {
            return false;
        }

        return validate(node.left, minVal, node.val) && validate(node.right, node.val, maxVal);
    }

    public static void main(String[] args) {
        Validate_Binary_Search_Tree solver = new Validate_Binary_Search_Tree();
        // Valid BST: 2 -> (1, 3)
        TreeNode root1 = new TreeNode(2);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(3);
        System.out.println("Is root1 a valid BST: " + solver.isValidBST(root1)); // true

        // Invalid BST: 5 -> (1, 4 -> (3, 6))
        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(4);
        root2.right.left = new TreeNode(3);
        root2.right.right = new TreeNode(6);
        System.out.println("Is root2 a valid BST: " + solver.isValidBST(root2)); // false
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
