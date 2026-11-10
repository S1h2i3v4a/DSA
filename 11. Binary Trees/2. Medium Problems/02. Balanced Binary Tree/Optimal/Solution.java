/*
QUESTION:
Determine if a binary tree is height-balanced in O(N).

APPROACH:
- Bottom-up height calculation.
- Return height if subtree is balanced, or `-1` if it is unbalanced.
- If left subtree returns -1 or right subtree returns -1 or `|lh - rh| > 1`, return -1.
- Else return `1 + max(lh, rh)`.

TIME COMPLEXITY: O(N) visiting each node once.
SPACE COMPLEXITY: O(H) recursion stack.
*/

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    private int checkHeight(TreeNode root) {
        if (root == null) return 0;

        int lh = checkHeight(root.left);
        if (lh == -1) return -1;

        int rh = checkHeight(root.right);
        if (rh == -1) return -1;

        if (Math.abs(lh - rh) > 1) return -1;
        return 1 + Math.max(lh, rh);
    }

    public boolean isBalanced(TreeNode root) {
        return checkHeight(root) != -1;
    }
}
