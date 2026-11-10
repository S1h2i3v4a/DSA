/*
QUESTION:
Given the root of a complete binary tree, return the number of the nodes in the tree.

APPROACH:
- Traverse the entire tree recursively and count nodes: `1 + countNodes(left) + countNodes(right)`.

TIME COMPLEXITY: O(N) visiting every node.
SPACE COMPLEXITY: O(log N) recursion stack.
*/

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
}
