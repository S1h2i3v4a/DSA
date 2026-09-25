/**
 * Problem Name: LRU Cache
 * Platform: LeetCode (146)
 * Difficulty: Medium
 * 
 * Time Complexity: O(1) for get(key) and put(key, value).
 * Space Complexity: O(Capacity) for HashMap and Doubly Linked List nodes.
 * 
 * Approach:
 * Doubly Linked List + HashMap.
 * - Map stores `key -> Node`.
 * - Doubly Linked List maintains usage order: dummy `head` (MRU) and dummy `tail` (LRU).
 * - `get(key)`: If key exists, move Node to head, return `node.val`; else -1.
 * - `put(key, value)`: If key exists, update val and move Node to head.
 *   If key doesn't exist and capacity full, remove node before tail (`tail.prev`) from Map & List, then insert new Node at head.
 */

import java.util.HashMap;
import java.util.Map;

class LRU_Cache {

    private static class Node {
        int key, val;
        Node prev, next;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    private final int capacity;
    private final Map<Integer, Node> map;
    private final Node head, tail;

    public LRU_Cache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        Node node = map.get(key);
        removeNode(node);
        insertAtHead(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
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

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void insertAtHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    public static void main(String[] args) {
        LRU_Cache lru = new LRU_Cache(2);
        lru.put(1, 1);
        lru.put(2, 2);
        System.out.println("Get 1: " + lru.get(1)); // 1
        lru.put(3, 3); // evicts key 2
        System.out.println("Get 2: " + lru.get(2)); // -1
        lru.put(4, 4); // evicts key 1
        System.out.println("Get 1: " + lru.get(1)); // -1
        System.out.println("Get 3: " + lru.get(3)); // 3
        System.out.println("Get 4: " + lru.get(4)); // 4
    }
}
