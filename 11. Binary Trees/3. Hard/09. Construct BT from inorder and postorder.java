/* QUESTION:

Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and postorder is the postorder traversal of the same tree, construct and return the binary tree.

APPROACH:

The postorder traversal follows the left-right-root order, while the inorder traversal follows the left-root-right order. Based on these two traversals, we can construct the binary tree recursively.

1. The last element in the postorder traversal array is the root of the binary tree.
2. We can find the position of the root in the inorder traversal array. All the elements to the left of this position will be in the left subtree, and all the elements to the right will be in the right subtree.
3. Recursively, we can build the left and right subtrees using the corresponding portions of the inorder and postorder traversal arrays.
4. The recursive function takes the index of the current element in the postorder traversal array, the starting and ending indices of the current portion in the inorder traversal array, and the inorder and postorder traversal arrays as inputs.
5. In each recursive call, we create a new node with the value of the current element in the postorder traversal array and determine its left and right subtrees by calling the recursive function on the corresponding portions of the inorder and postorder traversal arrays.
6. The base case is when the starting index is greater than the ending index, indicating an empty portion of the tree. In this case, we return NULL.
7. Finally, we return the root of the constructed binary tree.

COMPLEXITY ANALYSIS:

Let n be the number of nodes in the binary tree.
- Time Complexity: The time complexity of this approach is O(n), as we visit each node once.
- Space Complexity: The space complexity is O(n) for the recursive call stack.

CODE:
*/

import java.io.*;
import java.util.*;


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

// CODE:-
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
