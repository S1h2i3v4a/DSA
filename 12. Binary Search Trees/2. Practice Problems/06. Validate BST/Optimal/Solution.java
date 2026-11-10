/*
QUESTION:
Validate BST in O(N) time and O(H) space using valid range boundaries.

APPROACH:
- Each node must satisfy `min < node.val < max`.
- For root, range is `(-infinity, +infinity)`.
- Left child must satisfy `min < left.val < node.val`.
- Right child must satisfy `node.val < right.val < max`.

TIME COMPLEXITY: O(N) visiting each node once.
SPACE COMPLEXITY: O(H) recursion stack.
*/

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    private boolean validate(TreeNode node, Long min, Long max) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) return false;

        return validate(node.left, min, (long) node.val) &&
               validate(node.right, (long) node.val, max);
    }

    public boolean isValidBST(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
}
