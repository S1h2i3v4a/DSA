/**
 * Problem Name: Symmetric Tree
 * Platform: LeetCode (LC 101)
 * Difficulty: Easy
 * 
 * Time Complexity: O(N) where N is total number of nodes in tree
 * Space Complexity: O(H) recursion stack space
 * 
 * Approach:
 * Recursive Mirror Check:
 * A binary tree is symmetric if its left and right subtrees are mirror images of each other.
 * Two trees t1 and t2 are mirror images if:
 * 1. Both are null -> return true.
 * 2. One is null or t1.val != t2.val -> return false.
 * 3. t1.left is mirror of t2.right AND t1.right is mirror of t2.left.
 */

class Symmetric_Tree {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        if (t1.val != t2.val) return false;

        return isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left);
    }

    public static void main(String[] args) {
        Symmetric_Tree solver = new Symmetric_Tree();
        // Symmetric Tree: 1 -> (2 -> (3, 4), 2 -> (4, 3))
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(2);
        root1.left.left = new TreeNode(3);
        root1.left.right = new TreeNode(4);
        root1.right.left = new TreeNode(4);
        root1.right.right = new TreeNode(3);

        System.out.println("Is root1 symmetric: " + solver.isSymmetric(root1)); // true

        // Asymmetric Tree: 1 -> (2 -> (null, 3), 2 -> (null, 3))
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(2);
        root2.left.right = new TreeNode(3);
        root2.right.right = new TreeNode(3);

        System.out.println("Is root2 symmetric: " + solver.isSymmetric(root2)); // false
    }
}
