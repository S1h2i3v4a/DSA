/*
QUESTION:
Given the root of a binary tree, return the length of the diameter of the tree.
The diameter of a binary tree is the length of the longest path between any two nodes in a tree.

APPROACH:
- At each node, longest path passing through this node is `height(root.left) + height(root.right)`.
- Maximize over all nodes recursively.

TIME COMPLEXITY: O(N^2) computing height for each node.
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

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        int dCurrent = height(root.left) + height(root.right);
        int dLeft = diameterOfBinaryTree(root.left);
        int dRight = diameterOfBinaryTree(root.right);
        return Math.max(dCurrent, Math.max(dLeft, dRight));
    }
}
