/*
QUESTION:
Recover BST in O(N) time and O(H) space by identifying swapped nodes during inorder traversal.

APPROACH:
- Inorder traversal of BST should be strictly increasing.
- Track `prev`, `first`, `middle`, and `last` pointers:
  - If `prev != null && root.val < prev.val`:
    - First violation: `first = prev`, `middle = root`.
    - Second violation: `last = root`.
- After traversal:
  - If swapped nodes are non-adjacent: swap `first.val` and `last.val`.
  - If swapped nodes are adjacent: swap `first.val` and `middle.val`.

TIME COMPLEXITY: O(N) single pass.
SPACE COMPLEXITY: O(H) recursion stack.
*/

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    private TreeNode first;
    private TreeNode prev;
    private TreeNode middle;
    private TreeNode last;

    private void inorder(TreeNode root) {
        if (root == null) return;

        inorder(root.left);

        if (prev != null && root.val < prev.val) {
            if (first == null) {
                first = prev;
                middle = root;
            } else {
                last = root;
            }
        }
        prev = root;

        inorder(root.right);
    }

    public void recoverTree(TreeNode root) {
        first = middle = last = null;
        prev = new TreeNode(Integer.MIN_VALUE);

        inorder(root);

        if (first != null && last != null) {
            int temp = first.val;
            first.val = last.val;
            last.val = temp;
        } else if (first != null && middle != null) {
            int temp = first.val;
            first.val = middle.val;
            middle.val = temp;
        }
    }
}
