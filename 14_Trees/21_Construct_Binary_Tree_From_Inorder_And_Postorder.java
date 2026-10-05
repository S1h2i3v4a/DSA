/**
 * Problem Name: Construct Binary Tree from Inorder and Postorder Traversal
 * Platform: LeetCode (LC 106)
 * Difficulty: Medium
 * 
 * Time Complexity: O(N) where N is the number of nodes in the binary tree
 * Space Complexity: O(N) for HashMap lookup and recursion stack
 * 
 * Approach:
 * Divide and Conquer using Inorder Hash Map:
 * 1. Build a HashMap storing inorder[val] -> index to look up root indices in O(1).
 * 2. In postorder array, root is always at postEnd.
 * 3. Find root index in inorder array: inRoot.
 * 4. Number of nodes in left subtree = inRoot - inStart.
 * 5. Recurse left subtree:
 *    - inorder range:   [inStart, inRoot - 1]
 *    - postorder range: [postStart, postStart + numsLeft - 1]
 * 6. Recurse right subtree:
 *    - inorder range:   [inRoot + 1, inEnd]
 *    - postorder range: [postStart + numsLeft, postEnd - 1]
 */

import java.util.*;

class Construct_Binary_Tree_From_Inorder_And_Postorder {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }

        return build(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, inMap);
    }

    private TreeNode build(int[] inorder, int inStart, int inEnd,
                           int[] postorder, int postStart, int postEnd,
                           Map<Integer, Integer> inMap) {
        if (inStart > inEnd || postStart > postEnd) {
            return null;
        }

        TreeNode root = new TreeNode(postorder[postEnd]);
        int inRoot = inMap.get(root.val);
        int numsLeft = inRoot - inStart;

        root.left = build(inorder, inStart, inRoot - 1,
                          postorder, postStart, postStart + numsLeft - 1, inMap);

        root.right = build(inorder, inRoot + 1, inEnd,
                           postorder, postStart + numsLeft, postEnd - 1, inMap);

        return root;
    }

    // Helper method to print preorder traversal of reconstructed tree
    private static void printPreorder(TreeNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        printPreorder(root.left);
        printPreorder(root.right);
    }

    public static void main(String[] args) {
        Construct_Binary_Tree_From_Inorder_And_Postorder solver = new Construct_Binary_Tree_From_Inorder_And_Postorder();
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};

        TreeNode root = solver.buildTree(inorder, postorder);
        System.out.print("Reconstructed Tree Preorder: ");
        printPreorder(root);
        System.out.println(); // Expected: 3 9 20 15 7
    }
}
