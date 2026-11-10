/*
QUESTION:
Implement `BSTIterator` in O(1) amortized time and O(H) memory using a controlled Stack.

APPROACH:
- Use a Stack to store the path to the current smallest element.
- In constructor: push `root` and all of its left descendants.
- `next()`:
  - Pop top node `curr`.
  - Push all left descendants of `curr.right` onto stack.
  - Return `curr.val`.
- `hasNext()`: Return `!stack.isEmpty()`.

TIME COMPLEXITY: Amortized O(1) per `next` operation (each node pushed and popped at most once).
SPACE COMPLEXITY: O(H) where H is tree height.
*/

import java.util.Stack;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    private Stack<TreeNode> stack;

    public Solution(TreeNode root) {
        stack = new Stack<>();
        pushAllLeft(root);
    }

    private void pushAllLeft(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

    public int next() {
        TreeNode node = stack.pop();
        if (node.right != null) {
            pushAllLeft(node.right);
        }
        return node.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }
}
