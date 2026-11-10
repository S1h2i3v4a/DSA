/*
QUESTION:
Given the root of a binary tree, return the postorder traversal using 1 Stack.

APPROACH:
- Use a single stack and a pointer `curr = root`.
- Move down the left side pushing nodes onto stack.
- When reaching null:
  - Check right child of stack top: if right exists and was not just visited, move `curr` to right child.
  - Else: pop stack top, add to result, update `lastVisited = popped`.

TIME COMPLEXITY: O(N).
SPACE COMPLEXITY: O(N) using only one stack.
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
    public List<Integer> postorderOneStack(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        TreeNode lastVisited = null;

        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                TreeNode peekNode = stack.peek();
                if (peekNode.right != null && lastVisited != peekNode.right) {
                    curr = peekNode.right;
                } else {
                    result.add(peekNode.val);
                    lastVisited = stack.pop();
                }
            }
        }
        return result;
    }
}
