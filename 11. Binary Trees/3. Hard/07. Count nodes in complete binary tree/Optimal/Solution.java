/*
QUESTION:
Count nodes in a complete binary tree in less than O(N) time.

APPROACH:
- In a complete binary tree:
  - Find left height `lh` by following `node = node.left`.
  - Find right height `rh` by following `node = node.right`.
  - If `lh == rh`, the subtree is a full complete tree with `(1 << lh) - 1` nodes.
  - Else recurse: `1 + countNodes(root.left) + countNodes(root.right)`.

TIME COMPLEXITY: O((log N)^2) since at each step at least one subtree is full complete tree.
SPACE COMPLEXITY: O(log N) recursion stack.
*/

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    private int getLeftHeight(TreeNode root) {
        int h = 0;
        while (root != null) {
            h++;
            root = root.left;
        }
        return h;
    }

    private int getRightHeight(TreeNode root) {
        int h = 0;
        while (root != null) {
            h++;
            root = root.right;
        }
        return h;
    }

    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        int lh = getLeftHeight(root);
        int rh = getRightHeight(root);

        if (lh == rh) {
            return (1 << lh) - 1;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
}
