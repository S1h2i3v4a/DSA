/*
QUESTION:
Given a binary tree, determine if it is height-balanced.
A height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differs by more than one.

APPROACH:
- For each node: compute height of left subtree and height of right subtree.
- If `Math.abs(leftH - rightH) > 1`, return false.
- Recursively verify that left and right subtrees are also balanced.

TIME COMPLEXITY: O(N^2) computing height at every node.
SPACE COMPLEXITY: O(H) recursion stack.
*/

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    private int height(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(height(root.left), height(root.right));
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        int lh = height(root.left);
        int rh = height(root.right);
        if (Math.abs(lh - rh) > 1) return false;
        return isBalanced(root.left) && isBalanced(root.right);
    }
}
