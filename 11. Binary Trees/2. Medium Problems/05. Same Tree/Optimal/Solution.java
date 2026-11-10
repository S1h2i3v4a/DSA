/*
QUESTION:
Given the roots of two binary trees `p` and `q`, write a function to check if they are the same or not.
Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.

APPROACH:
- If both `p` and `q` are null: return true.
- If one is null and other is not: return false.
- If `p.val != q.val`: return false.
- Recursively check `isSameTree(p.left, q.left)` and `isSameTree(p.right, q.right)`.

TIME COMPLEXITY: O(min(N, M)) where N and M are number of nodes.
SPACE COMPLEXITY: O(min(H1, H2)) recursion stack.
*/

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
