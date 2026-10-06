/**
 * Problem Name: Lowest Common Ancestor of a Binary Search Tree
 * Platform: LeetCode (LC 235)
 * Difficulty: Medium
 * 
 * Time Complexity: O(H) where H is the height of the BST
 * Space Complexity: O(1) iterative approach requires constant extra space
 * 
 * Approach:
 * BST Invariant Traversal:
 * Exploit the ordered property of BST:
 * - If both p.val < curr.val and q.val < curr.val, LCA lies strictly in the left subtree.
 * - If both p.val > curr.val and q.val > curr.val, LCA lies strictly in the right subtree.
 * - Otherwise, curr is the split point (or curr equals p or q), meaning curr is the LCA.
 */

class Lowest_Common_Ancestor_In_BST {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode curr = root;

        while (curr != null) {
            if (p.val < curr.val && q.val < curr.val) {
                curr = curr.left;
            } else if (p.val > curr.val && q.val > curr.val) {
                curr = curr.right;
            } else {
                return curr; // Split point or matching node
            }
        }

        return null;
    }

    public static void main(String[] args) {
        Lowest_Common_Ancestor_In_BST solver = new Lowest_Common_Ancestor_In_BST();
        // BST: 6 -> (2 -> (0, 4 -> (3, 5)), 8 -> (7, 9))
        TreeNode root = new TreeNode(6);
        TreeNode node2 = new TreeNode(2);
        TreeNode node8 = new TreeNode(8);
        TreeNode node0 = new TreeNode(0);
        TreeNode node4 = new TreeNode(4);
        TreeNode node7 = new TreeNode(7);
        TreeNode node9 = new TreeNode(9);
        TreeNode node3 = new TreeNode(3);
        TreeNode node5 = new TreeNode(5);

        root.left = node2;
        root.right = node8;
        node2.left = node0;
        node2.right = node4;
        node4.left = node3;
        node4.right = node5;
        node8.left = node7;
        node8.right = node9;

        TreeNode lca1 = solver.lowestCommonAncestor(root, node2, node8);
        System.out.println("LCA of 2 and 8: " + (lca1 != null ? lca1.val : "null")); // Expected: 6

        TreeNode lca2 = solver.lowestCommonAncestor(root, node2, node4);
        System.out.println("LCA of 2 and 4: " + (lca2 != null ? lca2.val : "null")); // Expected: 2
    }
}
