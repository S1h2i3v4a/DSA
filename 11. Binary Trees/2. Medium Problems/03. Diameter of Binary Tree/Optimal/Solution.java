/*
QUESTION:
Find diameter of binary tree in O(N).

APPROACH:
- Compute height in a postorder traversal and update diameter simultaneously.
- At each node: `diameter = Math.max(diameter, leftHeight + rightHeight)`.
- Return `1 + Math.max(leftHeight, rightHeight)`.

TIME COMPLEXITY: O(N) visiting each node once.
SPACE COMPLEXITY: O(H) recursion stack.
*/

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    private int maxDiameter = 0;

    private int height(TreeNode root) {
        if (root == null) return 0;
        int lh = height(root.left);
        int rh = height(root.right);
        maxDiameter = Math.max(maxDiameter, lh + rh);
        return 1 + Math.max(lh, rh);
    }

    public int diameterOfBinaryTree(TreeNode root) {
        maxDiameter = 0;
        height(root);
        return maxDiameter;
    }
}
