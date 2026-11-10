/*
QUESTION:
Flatten binary tree to linked list in-place in O(1) auxiliary space using Morris threading.

APPROACH:
- While `curr != null`:
  - If `curr.left != null`:
    - Find predecessor: rightmost node of `curr.left`.
    - Connect `predecessor.right = curr.right`.
    - Move `curr.right = curr.left`.
    - Set `curr.left = null`.
  - Move `curr = curr.right`.

TIME COMPLEXITY: O(N) each edge traversed at most twice.
SPACE COMPLEXITY: O(1) constant auxiliary space.
*/

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    public void flatten(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left != null) {
                TreeNode prev = curr.left;
                while (prev.right != null) {
                    prev = prev.right;
                }
                prev.right = curr.right;
                curr.right = curr.left;
                curr.left = null;
            }
            curr = curr.right;
        }
    }
}
