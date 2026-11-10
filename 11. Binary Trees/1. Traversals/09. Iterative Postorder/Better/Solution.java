/*
QUESTION:
Given the root of a binary tree, return the postorder traversal using 2 Stacks.

APPROACH:
- Use two stacks `s1` and `s2`.
- Push root to `s1`.
- While `s1` is not empty:
  - Pop `curr` from `s1` and push to `s2`.
  - Push `curr.left` to `s1`.
  - Push `curr.right` to `s1`.
- Elements popped from `s2` form the postorder sequence (`Left -> Right -> Root`).

TIME COMPLEXITY: O(N).
SPACE COMPLEXITY: O(2 * N) for two stacks.
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
    public List<Integer> postorderTwoStacks(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        s1.push(root);

        while (!s1.isEmpty()) {
            TreeNode curr = s1.pop();
            s2.push(curr);

            if (curr.left != null) s1.push(curr.left);
            if (curr.right != null) s1.push(curr.right);
        }

        while (!s2.isEmpty()) {
            result.add(s2.pop().val);
        }
        return result;
    }
}
