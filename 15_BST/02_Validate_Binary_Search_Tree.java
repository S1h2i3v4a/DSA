/**
 * Problem Name: Validate Binary Search Tree
 * Platform: LeetCode (LC 98)
 * Difficulty: Medium
 * 
 * Time Complexity: O(N) where N is the total number of nodes in tree
 * Space Complexity: O(H) recursion stack space
 * 
 * Approach:
 * Range Validation (Min & Max Bounds):
 * For every node in a valid BST:
 *   - All nodes in its left subtree must have values strictly less than node.val.
 *   - All nodes in its right subtree must have values strictly greater than node.val.
 * Use long variables for minVal and maxVal bounds to handle Integer.MIN_VALUE and Integer.MAX_VALUE edges.
 * Recurse left with (minVal, node.val) and right with (node.val, maxVal).
 */

class Validate_Binary_Search_Tree {
    public boolean isValidBST(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean validate(TreeNode node, long minVal, long maxVal) {
        if (node == null) return true;

        if (node.val <= minVal || node.val >= maxVal) {
            return false;
        }

        return validate(node.left, minVal, node.val) && validate(node.right, node.val, maxVal);
    }

    public static void main(String[] args) {
        Validate_Binary_Search_Tree solver = new Validate_Binary_Search_Tree();
        // Valid BST: 2 -> (1, 3)
        TreeNode root1 = new TreeNode(2);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(3);
        System.out.println("Is root1 a valid BST: " + solver.isValidBST(root1)); // true

        // Invalid BST: 5 -> (1, 4 -> (3, 6))
        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(4);
        root2.right.left = new TreeNode(3);
        root2.right.right = new TreeNode(6);
        System.out.println("Is root2 a valid BST: " + solver.isValidBST(root2)); // false
    }
}
