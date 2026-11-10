/*
QUESTION:
Given the root of a binary tree, return the postorder traversal of its nodes' values.

APPROACH:
- Postorder traversal visits nodes in the order: Left -> Right -> Root.
- Recursively traverse left subtree, recursively traverse right subtree, then process root node.

TIME COMPLEXITY: O(N) visiting each node once.
SPACE COMPLEXITY: O(N) recursion stack.
*/

import java.util.ArrayList;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    private void dfs(TreeNode node, List<Integer> res) {
        if (node == null) return;
        dfs(node.left, res);
        dfs(node.right, res);
        res.add(node.val);
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res);
        return res;
    }
}
