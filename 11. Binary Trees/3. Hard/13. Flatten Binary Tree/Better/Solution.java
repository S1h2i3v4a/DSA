/*
QUESTION:
Given the root of a binary tree, flatten the tree into a "linked list":
- The "linked list" should use the same TreeNode class where the right child pointer points to the next node and the left is always null.
- The linked list should be in the same order as a pre-order traversal.

APPROACH:
- Reverse postorder recursion: `Right -> Left -> Root`.
- Maintain a pointer `prev` initialized to null.
- For current node:
  - Flatten right subtree.
  - Flatten left subtree.
  - Set `root.right = prev`, `root.left = null`.
  - Update `prev = root`.

TIME COMPLEXITY: O(N) visiting each node once.
SPACE COMPLEXITY: O(H) recursion stack.
*/

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    private TreeNode prev = null;

    public void flatten(TreeNode root) {
        if (root == null) return;
        flatten(root.right);
        flatten(root.left);

        root.right = prev;
        root.left = null;
        prev = root;
    }
}
