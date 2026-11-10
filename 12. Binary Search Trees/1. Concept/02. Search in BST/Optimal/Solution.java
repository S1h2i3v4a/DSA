/*
QUESTION:
You are given the root of a binary search tree (BST) and an integer `val`.
Find the node in the BST that the node's value equals `val` and return the subtree rooted with that node.
If such a node does not exist, return null.

APPROACH:
- While `root != null` and `root.val != val`:
  - If `val < root.val`: search left subtree (`root = root.left`).
  - Else: search right subtree (`root = root.right`).
- Return `root`.

TIME COMPLEXITY: O(H) where H is tree height (O(log N) for balanced BST, O(N) for skewed BST).
SPACE COMPLEXITY: O(1) iterative search.
*/

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        while (root != null && root.val != val) {
            root = (val < root.val) ? root.left : root.right;
        }
        return root;
    }
}
