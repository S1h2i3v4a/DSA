/*
QUESTION:
Given a binary search tree (BST), find the lowest common ancestor (LCA) node of two given nodes in the BST.

APPROACH:
- Use the BST property:
  - If both `p.val < root.val` and `q.val < root.val`: LCA is in left subtree (`root = root.left`).
  - If both `p.val > root.val` and `q.val > root.val`: LCA is in right subtree (`root = root.right`).
  - Otherwise, `root` is the split point, which is the LCA!

TIME COMPLEXITY: O(H) where H is tree height.
SPACE COMPLEXITY: O(1) iterative search.
*/

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            if (p.val < root.val && q.val < root.val) {
                root = root.left;
            } else if (p.val > root.val && q.val > root.val) {
                root = root.right;
            } else {
                return root;
            }
        }
        return null;
    }
}
