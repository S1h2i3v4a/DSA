/*
Question:
Given the root of a binary tree, calculate the vertical order traversal of the binary tree.
There may be multiple nodes in the same row and same column. In such a case, sort these nodes by their values.

Approach:
- We can perform a level order traversal of the binary tree while keeping track of the horizontal distance (hd) of each node from the root.
- For each node at position (row, col), its left and right children will be at positions (row + 1, col - 1) and (row + 1, col + 1) respectively.
- We can use a queue to perform the level order traversal. The queue will store pairs of (hd, node), where hd represents the horizontal distance and node represents the current node being processed.
- During the traversal, we maintain an unordered_map to store the nodes at each horizontal distance. The key in the map is the horizontal distance (hd), and the value is a vector of pairs representing the level and value of the nodes at that horizontal distance.
- After the traversal, we iterate over the keys in the map in ascending order and sort the nodes within each horizontal distance based on their levels. We then extract the values and add them to the result vector.
- Finally, we return the result vector containing the vertical order traversal.

Complexity Analysis:
- Since we visit each node once and perform constant time operations for each node, the time complexity of this approach is O(N), where N is the number of nodes in the binary tree.
- The space complexity is O(N), where N is the number of nodes in the binary tree. This space is used to store the nodes in the unordered_map during the traversal.

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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
