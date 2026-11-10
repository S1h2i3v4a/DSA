/*
QUESTION:
Find Lowest Common Ancestor (LCA) in a single pass in O(N) time and O(H) space.

APPROACH:
- Base case: if root is null or root == p or root == q, return root.
- Recurse on left and right subtrees.
- If both left and right return non-null, root is the LCA.
- If only one subtree returns non-null, return that non-null node.

TIME COMPLEXITY: O(N) single pass.
SPACE COMPLEXITY: O(H) recursion stack.
*/

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) return root;
        return (left != null) ? left : right;
    }
}
