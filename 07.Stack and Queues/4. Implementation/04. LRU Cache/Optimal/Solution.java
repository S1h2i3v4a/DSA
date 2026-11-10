/*
QUESTION:
Design a data structure that follows the constraints of a Least Recently Used (LRU) cache in O(1) time complexity for both get and put operations.

APPROACH:
- Use a Doubly Linked List (DLL) and a `HashMap<Integer, Node>`.
- The DLL has dummy `head` (most recent) and `tail` (least recent).
- `get(key)`:
  - If node exists, remove it from its current position in DLL and insert right after `head`. Return `node.val`.
  - Else return -1.
- `put(key, value)`:
  - If node exists, update `node.val`, move to head.
  - If not exists:
    - If cache is full, remove the node before `tail` from both DLL and map.
    - Create new node, insert right after `head`, add to map.

TIME COMPLEXITY: O(1) for both get and put operations.
SPACE COMPLEXITY: O(capacity) for HashMap and DLL nodes.
*/

import java.util.HashMap;
import java.util.Map;

class Solution {
    private static class Node {
        int key;
        int val;
        Node prev;
        Node next;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    private int capacity;
    private Map<Integer, Node> map;
    private Node head;
    private Node tail;

    public Solution(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    private void addNode(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        Node node = map.get(key);
        removeNode(node);
        addNode(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            removeNode(node);
            addNode(node);
        } else {
            if (map.size() == capacity) {
                Node lru = tail.prev;
                removeNode(lru);
                map.remove(lru.key);
            }
            Node newNode = new Node(key, value);
            map.put(key, newNode);
            addNode(newNode);
        }
    }
}
