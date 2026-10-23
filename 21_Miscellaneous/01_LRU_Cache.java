/**
 * Problem Name: LRU Cache (Least Recently Used Cache)
 * Platform: LeetCode (LC 146) / GeeksforGeeks
 * Difficulty: Medium
 *
 * Time Complexity: O(1) for both get() and put() operations
 * Space Complexity: O(capacity) for Doubly Linked List nodes and HashMap
 *
 * Approach:
 *   - Implement a data structure that follows the Least Recently Used (LRU) cache policy.
 *   - Data Structures:
 *     1. Doubly Linked List (DLL) with dummy `head` and `tail` nodes:
 *        - Most recently used nodes are moved directly to `head.next`.
 *        - Least recently used node resides at `tail.prev`.
 *     2. HashMap<Integer, Node>:
 *        - Provides O(1) key-to-node pointer lookup.
 *   - Operations:
 *     - get(key): If exists, move the accessed node to head (most recent) and return its value.
 *     - put(key, value): If key already exists, update value and move to head.
 *       If new key: create node, insert at head, put in map.
 *       If size exceeds capacity: remove LRU node (tail.prev) from DLL and remove from map.
 */

import java.util.HashMap;
import java.util.Map;

class LRUCacheDesign {

    static class Node {
        int key;
        int value;
        Node prev;
        Node next;

        Node(int k, int v) {
            this.key = k;
            this.value = v;
        }
    }

    static class LRUCache {
        private final int capacity;
        private final Map<Integer, Node> map;
        private final Node head;
        private final Node tail;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.map = new HashMap<>();
            this.head = new Node(-1, -1);
            this.tail = new Node(-1, -1);
            head.next = tail;
            tail.prev = head;
        }

        private void removeNode(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        private void insertAtHead(Node node) {
            node.next = head.next;
            node.next.prev = node;
            head.next = node;
            node.prev = head;
        }

        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }
            Node node = map.get(key);
            removeNode(node);
            insertAtHead(node); // Mark as most recently used
            return node.value;
        }

        public void put(int key, int value) {
            if (map.containsKey(key)) {
                Node node = map.get(key);
                node.value = value;
                removeNode(node);
                insertAtHead(node);
            } else {
                if (map.size() == capacity) {
                    Node lru = tail.prev;
                    map.remove(lru.key);
                    removeNode(lru);
                }
                Node newNode = new Node(key, value);
                map.put(key, newNode);
                insertAtHead(newNode);
            }
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println("Get 1: " + cache.get(1)); // returns 1
        cache.put(3, 3);                              // evicts key 2
        System.out.println("Get 2: " + cache.get(2)); // returns -1 (not found)
        cache.put(4, 4);                              // evicts key 1
        System.out.println("Get 1: " + cache.get(1)); // returns -1 (not found)
        System.out.println("Get 3: " + cache.get(3)); // returns 3
        System.out.println("Get 4: " + cache.get(4)); // returns 4
    }
}
