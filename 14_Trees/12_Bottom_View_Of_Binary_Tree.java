/**
 * Problem Name: Bottom View of Binary Tree
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
 * Maintain a TreeMap<col, val> and CONTINUOUSLY OVERWRITE the node value for each column index
 * (since BFS visits lower levels last, the bottom-most nodes remain in the map).
 */

import java.util.*;

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
