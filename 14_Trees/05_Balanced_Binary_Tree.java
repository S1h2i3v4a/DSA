/**
 * Problem Name: Balanced Binary Tree
 * Platform: LeetCode (LC 110)
 * Difficulty: Easy
 * 
 * Time Complexity: O(N) where N is the total number of nodes in the binary tree
 * Space Complexity: O(H) recursion stack space
 * 
 * Approach:
 * Bottom-Up DFS (Optimized Height Calculation):
 * Recurse left and right subtrees to compute height.
 * - If left height or right height is -1, or Math.abs(leftHeight - rightHeight) > 1: return -1 (unbalanced).
 * - Else return Math.max(leftHeight, rightHeight) + 1.
 * Tree is balanced if checkHeight(root) != -1.
 */

class Balanced_Binary_Tree {
    public boolean isBalanced(TreeNode root) {
        return checkHeight(root) != -1;
    }

    private int checkHeight(TreeNode node) {
        if (node == null) return 0;

        int leftHeight = checkHeight(node.left);
        if (leftHeight == -1) return -1;

        int rightHeight = checkHeight(node.right);
        if (rightHeight == -1) return -1;

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static void main(String[] args) {
        Balanced_Binary_Tree solver = new Balanced_Binary_Tree();
        // Balanced Tree: 3 -> (9, 20 -> (15, 7))
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(9);
        root1.right = new TreeNode(20);
        root1.right.left = new TreeNode(15);
        root1.right.right = new TreeNode(7);
        System.out.println("Is root1 balanced: " + solver.isBalanced(root1)); // true

        // Unbalanced Tree: 1 -> (2 -> (3 -> (4, 4), 3), 2)
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.left.left = new TreeNode(3);
        root2.left.left.left = new TreeNode(4);
        System.out.println("Is root2 balanced: " + solver.isBalanced(root2)); // false
    }
}
