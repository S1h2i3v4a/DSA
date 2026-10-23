/*
Question:
Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.

Basically, the deletion can be divided into two stages:

1. Search for a node to remove.
2. If the node is found, delete the node.

Approach:
To delete a node with a given key from the BST, we need to search for the node first. If the node is found, there are three possible cases:
1. The node to be deleted is a leaf node (no children).
2. The node to be deleted has only one child (left or right child).
3. The node to be deleted has both left and right children.

For the first case, we simply remove the node from the tree and return NULL as the new root.
For the second case, we return the non-NULL child of the node to be deleted as the new root.
For the third case, we find the maximum value node in the left subtree of the node to be deleted (the rightmost node of the left subtree), copy its value to the node to be deleted, and then recursively delete the maximum value node in the left subtree.

Example:
Input: root = [5,3,6,2,4,null,7], key = 3
Output: [5,4,6,2,null,null,7]
*/

// Time Complexity: O(log n) on average, O(n) in the worst case, where n is the number of nodes in the BST.
// The time complexity of the delete operation in a BST depends on the height of the tree, which is log n on average for a balanced BST. However, in the worst case, when the BST is skewed (all nodes have only one child), the time complexity becomes O(n).

// Space Complexity: O(log n) on average, O(n) in the worst case.
// The space complexity is determined by the recursion stack during the delete operation. In the average case, the maximum recursion depth is log n for a balanced BST. In the worst case, when the BST is skewed, the recursion depth becomes n.

import java.io.*;
import java.util.*;

// CODE:-
class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int x) { val = x; }
}

class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (root.val == key) return helper(root);
        TreeNode dummy = root;
        while (root != null) {
            if (root.val > key) {
                if (root.left != null && root.left.val == key) {
                    root.left = helper(root.left);
                    break;
                } else root = root.left;
            } else {
                if (root.right != null && root.right.val == key) {
                    root.right = helper(root.right);
                    break;
                } else root = root.right;
            }
        }
        return dummy;
    }
    private TreeNode helper(TreeNode root) {
        if (root.left == null) return root.right;
        if (root.right == null) return root.left;
        TreeNode rightChild = root.right;
        TreeNode lastRight = findLastRight(root.left);
        lastRight.right = rightChild;
        return root.left;
    }
    private TreeNode findLastRight(TreeNode root) {
        if (root.right == null) return root;
        return findLastRight(root.right);
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
