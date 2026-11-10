/*
QUESTION:
Given two integer arrays `inorder` and `postorder`, construct and return the binary tree.

APPROACH:
- Postorder sequence visits `Left -> Right -> Root`. Thus `postorder[postEnd]` is the root.
- Locate root in `inorder` using HashMap to determine sizes of left and right subtrees.
- Recursively construct subtrees.

TIME COMPLEXITY: O(N) linear time.
SPACE COMPLEXITY: O(N) for HashMap and recursion stack.
*/

import java.util.HashMap;
import java.util.Map;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    private TreeNode build(int[] inorder, int inStart, int inEnd,
                           int[] postorder, int postStart, int postEnd,
                           Map<Integer, Integer> inMap) {
        if (inStart > inEnd || postStart > postEnd) return null;

        TreeNode root = new TreeNode(postorder[postEnd]);
        int inRoot = inMap.get(root.val);
        int numsLeft = inRoot - inStart;

        root.left = build(inorder, inStart, inRoot - 1,
                          postorder, postStart, postStart + numsLeft - 1, inMap);
        root.right = build(inorder, inRoot + 1, inEnd,
                           postorder, postStart + numsLeft, postEnd - 1, inMap);
        return root;
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) inMap.put(inorder[i], i);
        return build(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, inMap);
    }
}
