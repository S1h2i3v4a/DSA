/*
QUESTION:
Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).

APPROACH:
- Helper function `isMirror(t1, t2)`:
  - If both null: return true.
  - If one null: return false.
  - If `t1.val != t2.val`: return false.
  - Recursively check outer subtrees `isMirror(t1.left, t2.right)` and inner subtrees `isMirror(t1.right, t2.left)`.

TIME COMPLEXITY: O(N) visiting each node once.
SPACE COMPLEXITY: O(H) recursion stack.
*/

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    private boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return (t1.val == t2.val) &&
               isMirror(t1.left, t2.right) &&
               isMirror(t1.right, t2.left);
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }
}
