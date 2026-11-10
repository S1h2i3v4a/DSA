/*
QUESTION:
Given the root of a binary tree, return the maximum path sum of any non-empty path.

APPROACH:
- Postorder bottom-up calculation.
- For each node:
  - Compute `leftGain = Math.max(0, maxGain(node.left))`.
  - Compute `rightGain = Math.max(0, maxGain(node.right))`.
  - Path sum turning at this node is `node.val + leftGain + rightGain`.
  - Update global `maxSum`.
  - Return to parent: `node.val + Math.max(leftGain, rightGain)`.

TIME COMPLEXITY: O(N) single pass.
SPACE COMPLEXITY: O(H) recursion stack.
*/

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    private int maxSum = Integer.MIN_VALUE;

    private int maxGain(TreeNode node) {
        if (node == null) return 0;
        int leftGain = Math.max(0, maxGain(node.left));
        int rightGain = Math.max(0, maxGain(node.right));

        maxSum = Math.max(maxSum, node.val + leftGain + rightGain);
        return node.val + Math.max(leftGain, rightGain);
    }

    public int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        maxGain(root);
        return maxSum;
    }
}
