/**
 * Problem Name: Top View of Binary Tree
 * Platform: GeeksforGeeks
 * Difficulty: Medium
 * 
 * Time Complexity: O(N log N) using TreeMap (or O(N) using HashMap with min/max col tracking)
 * Space Complexity: O(N) to store nodes in Queue and Map
 * 
 * Approach:
 * BFS Coordinate Tracking:
 * Assign column coordinate to each node (root = 0, left = col - 1, right = col + 1).
 * Traverse using Queue of Pair(node, col).
 * Maintain a TreeMap<col, val> storing only the FIRST node encountered at each column coordinate
 * (since BFS visits upper levels first, top-most nodes are recorded).
 */

import java.util.*;

class Top_View_Of_Binary_Tree {
    private static class Pair {
        TreeNode node;
        int col;

        Pair(TreeNode node, int col) {
            this.node = node;
            this.col = col;
        }
    }

    public ArrayList<Integer> topView(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) return result;

        TreeMap<Integer, Integer> map = new TreeMap<>();
        Queue<Pair> queue = new LinkedList<>();

        queue.offer(new Pair(root, 0));

        while (!queue.isEmpty()) {
            Pair curr = queue.poll();
            TreeNode node = curr.node;
            int col = curr.col;

            // Only put if column is not already visited (top-most element)
            if (!map.containsKey(col)) {
                map.put(col, node.val);
            }

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
        Top_View_Of_Binary_Tree solver = new Top_View_Of_Binary_Tree();
        // Tree: 1 -> (2 -> (4, 5), 3 -> (6, 7))
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        System.out.println("Top View: " + solver.topView(root));
        // Expected: [4, 2, 1, 3, 7]
    }
}
