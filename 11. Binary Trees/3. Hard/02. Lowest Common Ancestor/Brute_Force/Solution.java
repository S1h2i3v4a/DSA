/*
QUESTION:
Find Lowest Common Ancestor (LCA) of nodes `p` and `q` in a binary tree.

APPROACH:
- Find path from root to `p` and path from root to `q`.
- Traverse both path lists simultaneously until values diverge.
- Last common node is the LCA.

TIME COMPLEXITY: O(N) to find paths.
SPACE COMPLEXITY: O(N) to store paths.
*/

import java.util.ArrayList;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    private boolean getPath(TreeNode root, TreeNode target, List<TreeNode> path) {
        if (root == null) return false;
        path.add(root);
        if (root == target) return true;
        if (getPath(root.left, target, path) || getPath(root.right, target, path)) {
            return true;
        }
        path.remove(path.size() - 1);
        return false;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> pathP = new ArrayList<>();
        List<TreeNode> pathQ = new ArrayList<>();
        if (!getPath(root, p, pathP) || !getPath(root, q, pathQ)) return null;

        int i = 0;
        while (i < pathP.size() && i < pathQ.size() && pathP.get(i) == pathQ.get(i)) {
            i++;
        }
        return pathP.get(i - 1);
    }
}
