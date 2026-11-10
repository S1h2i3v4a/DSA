/*
QUESTION:
Given a root of a binary tree, find the maximum sum (or size) of all keys of any sub-tree which is also a Binary Search Tree (BST).

APPROACH:
- For every node in the binary tree:
  - Validate if the subtree rooted at this node is a valid BST.
  - If valid, compute its sum/size.
  - Track the maximum sum found across all nodes.

TIME COMPLEXITY: O(N^2) validation at every node.
SPACE COMPLEXITY: O(N) recursion stack.
*/

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    private int maxSum = 0;

    private boolean isValid(TreeNode root, long min, long max) {
        if (root == null) return true;
        if (root.val <= min || root.val >= max) return false;
        return isValid(root.left, min, root.val) && isValid(root.right, root.val, max);
    }

    private int getSum(TreeNode root) {
        if (root == null) return 0;
        return root.val + getSum(root.left) + getSum(root.right);
    }

    private void traverse(TreeNode root) {
        if (root == null) return;
        if (isValid(root, Long.MIN_VALUE, Long.MAX_VALUE)) {
            maxSum = Math.max(maxSum, getSum(root));
        }
        traverse(root.left);
        traverse(root.right);
    }

    public int maxSumBST(TreeNode root) {
        maxSum = 0;
        traverse(root);
        return maxSum;
    }
}
