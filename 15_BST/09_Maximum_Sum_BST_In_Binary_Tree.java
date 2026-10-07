/**
 * Problem Name: Maximum Sum BST in Binary Tree
 * Platform: LeetCode (LC 1373)
 * Difficulty: Hard
 * 
 * Time Complexity: O(N) post-order single pass traversal
 * Space Complexity: O(H) recursion stack space
 * 
 * Approach:
 * Bottom-Up Post-Order Traversal:
 * Return NodeValue(maxVal, minVal, sum, isBST) for each subtree.
 * A subtree rooted at node is a valid BST if:
 *   - left.isBST && right.isBST
 *   - left.maxVal < node.val && node.val < right.minVal
 * If valid:
 *   - sum = node.val + left.sum + right.sum
 *   - maxSum = Math.max(maxSum, sum)
 *   - Return NodeValue(Math.max(node.val, right.maxVal), Math.min(node.val, left.minVal), sum, true)
 * Else:
 *   - Return NodeValue(0, 0, 0, false)
 */

class Maximum_Sum_BST_In_Binary_Tree {
    private static class NodeValue {
        int maxVal;
        int minVal;
        int sum;
        boolean isBST;

        NodeValue(int minVal, int maxVal, int sum, boolean isBST) {
            this.minVal = minVal;
            this.maxVal = maxVal;
            this.sum = sum;
            this.isBST = isBST;
        }
    }

    private int maxSum = 0;

    public int maxSumBST(TreeNode root) {
        maxSum = 0;
        traverse(root);
        return maxSum;
    }

    private NodeValue traverse(TreeNode node) {
        if (node == null) {
            return new NodeValue(Integer.MAX_VALUE, Integer.MIN_VALUE, 0, true);
        }

        NodeValue left = traverse(node.left);
        NodeValue right = traverse(node.right);

        // Check if current subtree is a valid BST
        if (left.isBST && right.isBST && left.maxVal < node.val && node.val < right.minVal) {
            int currentSum = node.val + left.sum + right.sum;
            maxSum = Math.max(maxSum, currentSum);

            int minVal = Math.min(node.val, left.minVal);
            int maxVal = Math.max(node.val, right.maxVal);
            return new NodeValue(minVal, maxVal, currentSum, true);
        }

        return new NodeValue(0, 0, 0, false);
    }

    public static void main(String[] args) {
        Maximum_Sum_BST_In_Binary_Tree solver = new Maximum_Sum_BST_In_Binary_Tree();
        // Tree: 1 -> (4 -> (2, 4), 3 -> (2, 5 -> (null, 6)))
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(4);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(5);
        root.right.right.right = new TreeNode(6);

        System.out.println("Maximum Sum BST: " + solver.maxSumBST(root)); // Expected: 20
    }
}
