/*
QUESTION:
Given the root of a binary tree, return the preorder traversal of its nodes' values iteratively using a Stack.

APPROACH:
- Initialize a Stack and push `root`.
- While stack is not empty:
  - Pop node `curr`, add `curr.val` to result.
  - Push `curr.right` first (if non-null), so it will be popped after left child.
  - Push `curr.left` next (if non-null).

TIME COMPLEXITY: O(N) single pass through all nodes.
SPACE COMPLEXITY: O(N) for stack.
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            result.add(curr.val);

            if (curr.right != null) stack.push(curr.right);
            if (curr.left != null) stack.push(curr.left);
        }
        return result;
    }
}
