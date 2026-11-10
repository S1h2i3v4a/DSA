/*
QUESTION:
Implement the `BSTIterator` class that represents an iterator over the in-order traversal of a binary search tree (BST).

APPROACH:
- Pre-generate the entire inorder traversal list in constructor.
- Use a pointer `idx` to return `list.get(idx++)` in `next()` and check `idx < list.size()` in `hasNext()`.

TIME COMPLEXITY: O(N) constructor, O(1) for next and hasNext.
SPACE COMPLEXITY: O(N) storing all nodes.
*/

import java.util.ArrayList;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    private List<Integer> nodes;
    private int idx;

    public Solution(TreeNode root) {
        nodes = new ArrayList<>();
        idx = 0;
        inorder(root);
    }

    private void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        nodes.add(root.val);
        inorder(root.right);
    }

    public int next() {
        return nodes.get(idx++);
    }

    public boolean hasNext() {
        return idx < nodes.size();
    }
}
