/*
QUESTION:
Given the root of a binary tree, return the inorder traversal of its nodes' values iteratively using a Stack.

APPROACH:
- Maintain a pointer `curr = root` and an explicit Stack.
- While `curr != null` or stack is not empty:
  - While `curr != null`: push `curr` to stack, and move `curr = curr.left`.
  - When reaching null: pop node from stack, add its value to result, and move `curr = popped.right`.

TIME COMPLEXITY: O(N) each node visited and popped once.
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
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;

        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            result.add(curr.val);
            curr = curr.right;
        }
        return result;
    }
}
