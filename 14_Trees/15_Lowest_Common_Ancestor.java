/**
 * Problem Name: Lowest Common Ancestor of a Binary Tree
 * Platform: LeetCode (LC 236)
 * Difficulty: Medium
 * 
 * Time Complexity: O(N) where N is total number of nodes in tree
 * Space Complexity: O(H) recursion stack space
 * 
 * Approach:
 * Recursive DFS:
 * 1. Base Case: If root is null, or root == p, or root == q, return root.
 * 2. Recurse left: leftLCA = lowestCommonAncestor(root.left, p, q)
 * 3. Recurse right: rightLCA = lowestCommonAncestor(root.right, p, q)
 * 4. If both leftLCA and rightLCA are non-null, root is the Lowest Common Ancestor.
 * 5. Otherwise, return whichever child returned non-null (or null if both null).
 */

class Lowest_Common_Ancestor {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root;
        }

        return left != null ? left : right;
    }

    public static void main(String[] args) {
        Lowest_Common_Ancestor solver = new Lowest_Common_Ancestor();
        // Tree: 3 -> (5 -> (6, 2 -> (7, 4)), 1 -> (0, 8))
        TreeNode root = new TreeNode(3);
        TreeNode node5 = new TreeNode(5);
        TreeNode node1 = new TreeNode(1);
        TreeNode node6 = new TreeNode(6);
        TreeNode node2 = new TreeNode(2);
        TreeNode node0 = new TreeNode(0);
        TreeNode node8 = new TreeNode(8);
        TreeNode node7 = new TreeNode(7);
        TreeNode node4 = new TreeNode(4);

        root.left = node5;
        root.right = node1;
        node5.left = node6;
        node5.right = node2;
        node1.left = node0;
        node1.right = node8;
        node2.left = node7;
        node2.right = node4;

        TreeNode lca = solver.lowestCommonAncestor(root, node5, node1);
        System.out.println("LCA of 5 and 1: " + (lca != null ? lca.val : "null")); // Expected: 3

        TreeNode lca2 = solver.lowestCommonAncestor(root, node5, node4);
        System.out.println("LCA of 5 and 4: " + (lca2 != null ? lca2.val : "null")); // Expected: 5
    }
}
