/*
QUESTION:
Given a Binary Tree of size N, find all root to leaf paths.

APPROACH:
- Use DFS backtracking.
- Add `node.val` to current path.
- If node is a leaf, add a copy of the path to result.
- Else recurse on left and right children.
- Backtrack by removing the last added element.

TIME COMPLEXITY: O(N) visiting each node.
SPACE COMPLEXITY: O(H) recursion stack and current path list.
*/

import java.util.ArrayList;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    private void findPaths(TreeNode root, ArrayList<Integer> current, ArrayList<ArrayList<Integer>> result) {
        if (root == null) return;
        current.add(root.val);

        if (root.left == null && root.right == null) {
            result.add(new ArrayList<>(current));
        } else {
            findPaths(root.left, current, result);
            findPaths(root.right, current, result);
        }

        current.remove(current.size() - 1);
    }

    public ArrayList<ArrayList<Integer>> Paths(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        findPaths(root, new ArrayList<>(), result);
        return result;
    }
}
