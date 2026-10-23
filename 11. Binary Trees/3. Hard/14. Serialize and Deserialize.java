/* QUESTION:

Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

APPROACH:

To serialize the binary tree, we can perform a preorder traversal of the tree and append the node values to a string, separating them by a delimiter.

To deserialize the string back to the original tree structure, we can use a stringstream to split the string by the delimiter and recursively build the tree.

1. For serialization:
   - If the current node is NULL, we append "N," to the string.
   - Otherwise, we append the node value followed by a delimiter "," to the string.
   - We then recursively serialize the left and right subtrees.

2. For deserialization:
   - If the current string token is "N," indicating a NULL node, we return NULL.
   - Otherwise, we convert the string token to an integer and create a new TreeNode with the value.
   - We then recursively deserialize the left and right subtrees.

COMPLEXITY ANALYSIS:

Let n be the number of nodes in the binary tree.
- Time Complexity:
  - Serialization: O(n) - We visit each node once during the serialization process.
  - Deserialization: O(n) - We process each string token once during the deserialization process.
- Space Complexity: O(n) - The space required for the serialized string and the recursion stack.

CODE:
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

// TIME COMPLEXITY = O(N)
// SPACE COMPLEXITY = O(1)
