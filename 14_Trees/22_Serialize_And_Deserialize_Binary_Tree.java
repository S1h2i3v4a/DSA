/**
 * Problem Name: Serialize and Deserialize Binary Tree
 * Platform: LeetCode (LC 297)
 * Difficulty: Hard
 * 
 * Time Complexity: O(N) for both serialize and deserialize
 * Space Complexity: O(N) for string representation and queue
 * 
 * Approach:
 * BFS Level-Order Traversal:
 * 1. Serialize:
 *    - Use a Queue for level order traversal.
 *    - Append node values separated by commas.
 *    - Append "#" for null nodes.
 * 2. Deserialize:
 *    - Split the serialized string by commas.
 *    - If first element is "#", return null.
 *    - Create root with first value and push to Queue.
 *    - Iterate through values array: for each parent in Queue, assign next token as left child,
 *      and the token after as right child (unless token is "#").
 */

import java.util.*;

class Serialize_And_Deserialize_Binary_Tree {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "#";

        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr == null) {
                sb.append("#,");
            } else {
                sb.append(curr.val).append(",");
                queue.offer(curr.left);
                queue.offer(curr.right);
            }
        }

        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.equals("#") || data.isEmpty()) return null;

        String[] values = data.split(",");
        if (values.length == 0 || values[0].equals("#")) return null;

        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int i = 1;
        while (!queue.isEmpty() && i < values.length) {
            TreeNode parent = queue.poll();

            // Left child
            if (!values[i].equals("#")) {
                TreeNode leftChild = new TreeNode(Integer.parseInt(values[i]));
                parent.left = leftChild;
                queue.offer(leftChild);
            }
            i++;

            // Right child
            if (i < values.length && !values[i].equals("#")) {
                TreeNode rightChild = new TreeNode(Integer.parseInt(values[i]));
                parent.right = rightChild;
                queue.offer(rightChild);
            }
            i++;
        }

        return root;
    }

    public static void main(String[] args) {
        Serialize_And_Deserialize_Binary_Tree codec = new Serialize_And_Deserialize_Binary_Tree();
        // Tree: 1 -> (2, 3 -> (4, 5))
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        String serialized = codec.serialize(root);
        System.out.println("Serialized Tree: " + serialized);

        TreeNode deserialized = codec.deserialize(serialized);
        String reSerialized = codec.serialize(deserialized);
        System.out.println("Re-serialized Tree: " + reSerialized);
        System.out.println("Roundtrip match: " + serialized.equals(reSerialized)); // true
    }
}
