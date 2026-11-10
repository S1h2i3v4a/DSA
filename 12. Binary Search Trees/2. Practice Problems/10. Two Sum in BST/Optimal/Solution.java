/*
QUESTION:
Find two sum in BST in O(H) space using Two BST Iterators (forward and reverse).

APPROACH:
- Use two custom BST iterators:
  - `leftIterator`: performs standard inorder traversal (gives smallest next element).
  - `rightIterator`: performs reverse inorder traversal (gives largest next element).
- Two-pointer technique:
  - If `val1 + val2 == k`, return true.
  - If `val1 + val2 < k`, advance `leftIterator`.
  - Else advance `rightIterator`.

TIME COMPLEXITY: O(N) total.
SPACE COMPLEXITY: O(2 * H) = O(H) for two iterator stacks.
*/

import java.util.Stack;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

class BSTIterator {
    private Stack<TreeNode> stack = new Stack<>();
    private boolean reverse;

    public BSTIterator(TreeNode root, boolean isReverse) {
        this.reverse = isReverse;
        pushAll(root);
    }

    private void pushAll(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = reverse ? node.right : node.left;
        }
    }

    public int next() {
        TreeNode node = stack.pop();
        if (reverse) pushAll(node.left);
        else pushAll(node.right);
        return node.val;
    }
}

class Solution {
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) return false;

        BSTIterator l = new BSTIterator(root, false);
        BSTIterator r = new BSTIterator(root, true);

        int i = l.next();
        int j = r.next();

        while (i < j) {
            if (i + j == k) return true;
            if (i + j < k) i = l.next();
            else j = r.next();
        }
        return false;
    }
}
