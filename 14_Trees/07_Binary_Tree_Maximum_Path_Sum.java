/**
 * Problem Name: Binary Tree Maximum Path Sum
 * Platform: LeetCode (LC 124)
 * Difficulty: Hard
 * 
 * Time Complexity: O(N) single pass post-order traversal
 * Space Complexity: O(H) recursion stack space
 * 
 * Approach:
 * Bottom-Up DFS:
 * Calculate the max contribution a subtree node can offer to its parent:
 * - leftMax = Math.max(0, maxGain(node.left))
 * - rightMax = Math.max(0, maxGain(node.right))
 * At the current node, maximum path sum passing through it is:
 * currentPathSum = node.val + leftMax + rightMax.
 * Update global maxPathSum = Math.max(maxPathSum, currentPathSum).
 * Return node.val + Math.max(leftMax, rightMax) to parent.
 */

class Binary_Tree_Maximum_Path_Sum {
    private int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        maxGain(root);
        return maxSum;
    }

    private int maxGain(TreeNode node) {
        if (node == null) return 0;

        // Ignore negative path sums by using Math.max(0, ...)
        int leftGain = Math.max(0, maxGain(node.left));
        int rightGain = Math.max(0, maxGain(node.right));

        int currentPathSum = node.val + leftGain + rightGain;
        maxSum = Math.max(maxSum, currentPathSum);

        return node.val + Math.max(leftGain, rightGain);
    }

    public static void main(String[] args) {
        Binary_Tree_Maximum_Path_Sum solver = new Binary_Tree_Maximum_Path_Sum();
        // Tree: -10 -> (9, 20 -> (15, 7))
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println("Maximum Path Sum: " + solver.maxPathSum(root)); // Expected: 42 (15 + 20 + 7)
    }
}
