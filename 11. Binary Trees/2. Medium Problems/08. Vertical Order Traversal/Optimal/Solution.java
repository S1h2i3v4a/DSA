/*
QUESTION:
Given the root of a binary tree, calculate the vertical order traversal of the binary tree.
Nodes at the same row and column must be sorted by their values.

APPROACH:
- Assign coordinates `(row, col)` where root is `(0, 0)`.
- Left child is `(row + 1, col - 1)`, right child is `(row + 1, col + 1)`.
- Use a `TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>>` to map `col -> (row -> minHeap of values)`.
- Traverse BFS / DFS, populate coordinates, and reconstruct final list.

TIME COMPLEXITY: O(N log N) using TreeMap and PriorityQueue sorting.
SPACE COMPLEXITY: O(N) to store coordinate mappings.
*/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

class Tuple {
    TreeNode node;
    int row, col;
    Tuple(TreeNode node, int row, int col) {
        this.node = node;
        this.row = row;
        this.col = col;
    }
}

class Solution {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();
        Queue<Tuple> queue = new LinkedList<>();
        if (root != null) queue.offer(new Tuple(root, 0, 0));

        while (!queue.isEmpty()) {
            Tuple curr = queue.poll();
            TreeNode node = curr.node;
            int x = curr.col;
            int y = curr.row;

            map.computeIfAbsent(x, k -> new TreeMap<>())
               .computeIfAbsent(y, k -> new PriorityQueue<>())
               .offer(node.val);

            if (node.left != null) queue.offer(new Tuple(node.left, y + 1, x - 1));
            if (node.right != null) queue.offer(new Tuple(node.right, y + 1, x + 1));
        }

        List<List<Integer>> result = new ArrayList<>();
        for (TreeMap<Integer, PriorityQueue<Integer>> ys : map.values()) {
            List<Integer> colList = new ArrayList<>();
            for (PriorityQueue<Integer> pq : ys.values()) {
                while (!pq.isEmpty()) {
                    colList.add(pq.poll());
                }
            }
            result.add(colList);
        }
        return result;
    }
}
