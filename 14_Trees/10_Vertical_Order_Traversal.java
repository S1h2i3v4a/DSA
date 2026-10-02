/**
 * Problem Name: Vertical Order Traversal of a Binary Tree
 * Platform: LeetCode (LC 987)
 * Difficulty: Hard
 * 
 * Time Complexity: O(N log N) due to sorting node positions at identical (row, col) coordinates
 * Space Complexity: O(N) to store coordinate nodes in data structures
 * 
 * Approach:
 * BFS with Coordinate Mapping:
 * Assign coordinates (col, row) to each node:
 *   - Root is at (0, 0).
 *   - Left child is at (col - 1, row + 1).
 *   - Right child is at (col + 1, row + 1).
 * Use a TreeMap<col, TreeMap<row, PriorityQueue<val>>> to automatically sort columns and rows,
 * while using PriorityQueue to sort values at identical (col, row) coordinates.
 */

import java.util.*;

class Vertical_Order_Traversal {
    private static class NodeInfo {
        TreeNode node;
        int col;
        int row;

        NodeInfo(TreeNode node, int col, int row) {
            this.node = node;
            this.col = col;
            this.row = row;
        }
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        // col -> row -> PriorityQueue of node values
        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();
        Queue<NodeInfo> queue = new LinkedList<>();

        queue.offer(new NodeInfo(root, 0, 0));

        while (!queue.isEmpty()) {
            NodeInfo info = queue.poll();
            TreeNode curr = info.node;
            int col = info.col;
            int row = info.row;

            map.putIfAbsent(col, new TreeMap<>());
            map.get(col).putIfAbsent(row, new PriorityQueue<>());
            map.get(col).get(row).offer(curr.val);

            if (curr.left != null) {
                queue.offer(new NodeInfo(curr.left, col - 1, row + 1));
            }
            if (curr.right != null) {
                queue.offer(new NodeInfo(curr.right, col + 1, row + 1));
            }
        }

        for (TreeMap<Integer, PriorityQueue<Integer>> colMap : map.values()) {
            List<Integer> colList = new ArrayList<>();
            for (PriorityQueue<Integer> pq : colMap.values()) {
                while (!pq.isEmpty()) {
                    colList.add(pq.poll());
                }
            }
            result.add(colList);
        }

        return result;
    }

    public static void main(String[] args) {
        Vertical_Order_Traversal solver = new Vertical_Order_Traversal();
        // Tree: 3 -> (9, 20 -> (15, 7))
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println("Vertical Order Traversal: " + solver.verticalTraversal(root));
        // Expected: [[9], [3, 15], [20], [7]]
    }
}
