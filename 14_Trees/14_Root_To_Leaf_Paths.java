/**
 * Problem Name: Root to Leaf Paths
 * Platform: GeeksforGeeks / LeetCode 257
 * Difficulty: Medium
 * 
 * Time Complexity: O(N) where N is total number of nodes in tree
 * Space Complexity: O(H) recursion stack space
 * 
 * Approach:
 * Backtracking DFS:
 * Traverse tree from root.
 * Append node value to current path array.
 * When a leaf node is reached (node.left == null && node.right == null):
 *   Add a copy of the current path to the result list.
 * Recurse left and right subtrees, then remove last element to backtrack.
 */

import java.util.*;

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
