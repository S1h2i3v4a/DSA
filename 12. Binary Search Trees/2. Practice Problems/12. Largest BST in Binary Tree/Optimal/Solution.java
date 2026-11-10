/*
QUESTION:
Find the maximum sum of all keys of any sub-tree which is also a BST in O(N).

APPROACH:
- Bottom-up postorder traversal returning a NodeValue:
  `{isBST, minVal, maxVal, sum}`.
- For current node:
  - If `left.isBST && right.isBST && left.maxVal < node.val && node.val < right.minVal`:
    - Valid BST! `sum = node.val + left.sum + right.sum`.
    - Update global `maxSum`.
    - Return valid NodeValue.
  - Else:
    - Invalid BST, return invalid NodeValue.

TIME COMPLEXITY: O(N) single pass.
SPACE COMPLEXITY: O(H) recursion stack.
*/

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    private static class NodeValue {
        boolean isBST;
        int minVal, maxVal, sum;

        NodeValue(boolean isBST, int minVal, int maxVal, int sum) {
            this.isBST = isBST;
            this.minVal = minVal;
            this.maxVal = maxVal;
            this.sum = sum;
        }
    }

    private int maxSum = 0;

    private NodeValue postorder(TreeNode root) {
        if (root == null) {
            return new NodeValue(true, Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
        }

        NodeValue left = postorder(root.left);
        NodeValue right = postorder(root.right);

        if (left.isBST && right.isBST && left.maxVal < root.val && root.val < right.minVal) {
            int currentSum = root.val + left.sum + right.sum;
            maxSum = Math.max(maxSum, currentSum);
            int minVal = Math.min(root.val, left.minVal);
            int maxVal = Math.max(root.val, right.maxVal);
            return new NodeValue(true, minVal, maxVal, currentSum);
        }

        return new NodeValue(false, 0, 0, 0);
    }

    public int maxSumBST(TreeNode root) {
        maxSum = 0;
        postorder(root);
        return maxSum;
    }
}
