/**
 * Problem Name: LFU Cache (Least Frequently Used Cache)
 * Platform: LeetCode (LC 460) / GeeksforGeeks
 * Difficulty: Hard
 *
 * Time Complexity: O(1) average time for both get() and put()
 * Space Complexity: O(capacity) for nodes, frequency maps, and doubly linked lists
 *
 * Approach:
 *   - Follows the Least Frequently Used eviction policy. When tied, the Least Recently Used
 *     key among the lowest frequency is evicted.
 *   - Data Structures:
 *     1. Node(key, value, frequency)
 *     2. DoublyLinkedList: Stores nodes of the SAME frequency in LRU order (head = most recent, tail = least recent).
 *     3. Map<Integer, Node> keyToNode: O(1) lookup from key to node.
 *     4. Map<Integer, DoublyLinkedList> freqToList: Maps frequency to its DoublyLinkedList.
 *     5. minFreq: An integer tracking the current lowest frequency among all stored elements.
 *   - Operations:
 *     - get(key): Fetch node, increment its frequency, move node to freq+1 list, update minFreq.
 *     - put(key, value): If key exists, update value and increment frequency.
 *       If new key:
 *         If at capacity, remove the tail node from freqToList.get(minFreq), remove from keyToNode.
 *         Add new node with freq = 1 to freqToList.get(1), set minFreq = 1.
 */

import java.util.HashMap;
import java.util.Map;

class LFUCacheDesign {

    static class Node {
        int key;
        int value;
        int freq;
        Node prev;
        Node next;

        Node(int k, int v) {
            this.key = k;
            this.value = v;
            this.freq = 1;
        }
    }

    static class DoublyLinkedList {
        Node head;
        Node tail;
        int size;

        DoublyLinkedList() {
            head = new Node(-1, -1);
            tail = new Node(-1, -1);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        void addNode(Node node) {
            node.next = head.next;
            node.next.prev = node;
            head.next = node;
            node.prev = head;
            size++;
        }

        void removeNode(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }

        Node removeTail() {
            if (size > 0) {
                Node lru = tail.prev;
                removeNode(lru);
                return lru;
            }
            return null;
        }
    }

    static class LFUCache {
        private final int capacity;
        private int minFreq;
        private final Map<Integer, Node> keyToNode;
        private final Map<Integer, DoublyLinkedList> freqToList;

        public LFUCache(int capacity) {
            this.capacity = capacity;
            this.minFreq = 0;
            this.keyToNode = new HashMap<>();
            this.freqToList = new HashMap<>();
        }

        private void updateFreq(Node node) {
            int oldFreq = node.freq;
            DoublyLinkedList oldList = freqToList.get(oldFreq);
            oldList.removeNode(node);

            if (oldFreq == minFreq && oldList.size == 0) {
                minFreq++;
            }

            node.freq++;
            DoublyLinkedList newList = freqToList.computeIfAbsent(node.freq, k -> new DoublyLinkedList());
            newList.addNode(node);
        }

        public int get(int key) {
            if (!keyToNode.containsKey(key)) {
                return -1;
            }
            Node node = keyToNode.get(key);
            updateFreq(node);
            return node.value;
        }

        public void put(int key, int value) {
            if (capacity == 0) return;

            if (keyToNode.containsKey(key)) {
                Node node = keyToNode.get(key);
                node.value = value;
                updateFreq(node);
            } else {
                if (keyToNode.size() == capacity) {
                    DoublyLinkedList minList = freqToList.get(minFreq);
                    Node evicted = minList.removeTail();
                    keyToNode.remove(evicted.key);
                }
                Node newNode = new Node(key, value);
                minFreq = 1;
                keyToNode.put(key, newNode);
                DoublyLinkedList list1 = freqToList.computeIfAbsent(1, k -> new DoublyLinkedList());
                list1.addNode(newNode);
            }
        }
    }

    public static void main(String[] args) {
        LFUCache lfu = new LFUCache(2);
        lfu.put(1, 1);
        lfu.put(2, 2);
        System.out.println("Get 1: " + lfu.get(1)); // returns 1
        lfu.put(3, 3);                              // evicts key 2 (key 2 has freq 1, key 1 has freq 2)
        System.out.println("Get 2: " + lfu.get(2)); // returns -1 (not found)
        System.out.println("Get 3: " + lfu.get(3)); // returns 3
        lfu.put(4, 4);                              // evicts key 1 (key 1 and 3 both freq 2, key 1 is LRU)
        System.out.println("Get 1: " + lfu.get(1)); // returns -1 (not found)
        System.out.println("Get 3: " + lfu.get(3)); // returns 3
        System.out.println("Get 4: " + lfu.get(4)); // returns 4
    }
}
