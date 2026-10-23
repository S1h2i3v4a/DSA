/*
Question:
Given a binary tree, print its bottom view.

Approach:
- We can perform a level order traversal of the binary tree while keeping track of the horizontal distance (hd) and the level of each node from the root.
- For each node, we update its horizontal distance and level in the map.
- Since we want to print the nodes in the order of their horizontal distance, we maintain the minimum and maximum horizontal distances (`mini` and `maxi`) during the traversal.
- Finally, we iterate over the range from `mini` to `maxi` and retrieve the corresponding values from the map, and add them to the result vector.

Complexity Analysis:
- Since we visit each node once and perform constant time operations for each node, the time complexity of this approach is O(N), where N is the number of nodes in the binary tree.
- The space complexity is O(N), where N is the number of nodes in the binary tree. This space is used to store the nodes in the map during the traversal.

Code:
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
class Bottom_View_Of_Binary_Tree {
    private static class Pair {
        TreeNode node;
        int col;

        Pair(TreeNode node, int col) {
            this.node = node;
            this.col = col;
        }
    }

    public ArrayList<Integer> bottomView(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) return result;

        TreeMap<Integer, Integer> map = new TreeMap<>();
        Queue<Pair> queue = new LinkedList<>();

        queue.offer(new Pair(root, 0));

        while (!queue.isEmpty()) {
            Pair curr = queue.poll();
            TreeNode node = curr.node;
            int col = curr.col;

            // Overwrite value to keep the bottom-most node at this column
            map.put(col, node.val);

            if (node.left != null) {
                queue.offer(new Pair(node.left, col - 1));
            }
            if (node.right != null) {
                queue.offer(new Pair(node.right, col + 1));
            }
        }

        for (int val : map.values()) {
            result.add(val);
        }

        return result;
    }

    public static void main(String[] args) {
        Bottom_View_Of_Binary_Tree solver = new Bottom_View_Of_Binary_Tree();
        // Tree: 20 -> (8 -> (5, 3 -> (10, 14)), 22 -> (null, 25))
        TreeNode root = new TreeNode(20);
        root.left = new TreeNode(8);
        root.right = new TreeNode(22);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(3);
        root.left.right.left = new TreeNode(10);
        root.left.right.right = new TreeNode(14);
        root.right.right = new TreeNode(25);

        System.out.println("Bottom View: " + solver.bottomView(root));
        // Expected: [5, 10, 3, 14, 25]
    }
}

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
