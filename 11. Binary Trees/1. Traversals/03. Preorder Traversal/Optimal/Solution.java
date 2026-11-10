/*
QUESTION:
Given the root of a binary tree, return the preorder traversal of its nodes' values.

APPROACH:
- Preorder traversal visits nodes in the order: Root -> Left -> Right.
- Use a recursive helper function to visit current node, then traverse left subtree, then traverse right subtree.

TIME COMPLEXITY: O(N) visiting each node once.
SPACE COMPLEXITY: O(N) recursion stack in worst case (skewed tree).
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
        res.add(node.val);
        dfs(node.left, res);
        dfs(node.right, res);
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res);
        return res;
    }
}
