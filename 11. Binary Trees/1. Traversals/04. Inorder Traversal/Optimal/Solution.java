/*
QUESTION:
Given the root of a binary tree, return the inorder traversal of its nodes' values.

APPROACH:
- Inorder traversal visits nodes in the order: Left -> Root -> Right.
- Recursively traverse left subtree, process root node, then recursively traverse right subtree.

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
        res.add(node.val);
        dfs(node.right, res);
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res);
        return res;
    }
}
