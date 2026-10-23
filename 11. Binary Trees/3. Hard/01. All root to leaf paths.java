/**Question:**

Write a program to print the path from the root to a given node in a binary tree. You are given a binary tree and a node value. You need to find and print the path from the root to the node.

**Approach:**

To find the path from the root to the given node, we can use a recursive function. The idea is to traverse the tree from the root and keep track of the path in a vector. If the target node is found, add the node's value to the path vector, and return true. Otherwise, recursively search for the target node in the left and right subtrees. If the target node is not found, remove the last node from the path vector before returning false.

**Complexity Analysis:**

Let N be the number of nodes in the binary tree.

- Time Complexity: The time complexity of the recursive function is O(N) as we may visit all nodes in the worst case.
- Space Complexity: The space complexity is O(N) due to the space used by the recursion stack and the path vector.

**Code:**/

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
class Root_To_Leaf_Paths {
    public ArrayList<ArrayList<Integer>> Paths(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        ArrayList<Integer> currentPath = new ArrayList<>();
        findPaths(root, currentPath, result);
        return result;
    }

    private void findPaths(TreeNode node, ArrayList<Integer> currentPath, ArrayList<ArrayList<Integer>> result) {
        if (node == null) return;

        currentPath.add(node.val);

        if (node.left == null && node.right == null) {
            result.add(new ArrayList<>(currentPath));
        } else {
            findPaths(node.left, currentPath, result);
            findPaths(node.right, currentPath, result);
        }

        // Backtrack
        currentPath.remove(currentPath.size() - 1);
    }

    public static void main(String[] args) {
        Root_To_Leaf_Paths solver = new Root_To_Leaf_Paths();
        // Tree: 1 -> (2 -> (null, 5), 3)
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);

        System.out.println("Root to Leaf Paths: " + solver.Paths(root));
        // Expected: [[1, 2, 5], [1, 3]]
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
