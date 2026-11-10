/*
QUESTION:
Given an array of integers `v`, create a binary tree in level order representation.
`root0` is given as the root with value `v.get(0)`.

APPROACH:
- Use a queue to build the binary tree level-by-level.
- For node at index `i`, its left child is at `2*i + 1` and right child is at `2*i + 2`.
- Enqueue children and link them to their parent.

TIME COMPLEXITY: O(N) where N is number of nodes.
SPACE COMPLEXITY: O(N) for queue storage.
*/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Node {
    int data;
    Node left, right;
    Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

class Solution {
    public static void create_tree(Node root0, ArrayList<Integer> v) {
        if (v == null || v.isEmpty()) return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root0);

        int i = 1;
        while (!queue.isEmpty() && i < v.size()) {
            Node curr = queue.poll();

            if (i < v.size()) {
                curr.left = new Node(v.get(i++));
                queue.add(curr.left);
            }
            if (i < v.size()) {
                curr.right = new Node(v.get(i++));
                queue.add(curr.right);
            }
        }
    }
}
