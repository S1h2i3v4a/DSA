/**
 * Problem Name: LFU Cache
 * Platform: LeetCode (460)
 * Difficulty: Hard
 * 
 * Time Complexity: O(1) for get(key) and put(key, value).
 * Space Complexity: O(Capacity) for storage maps and nodes.
 * 
 * Approach:
 * Frequency Doubly Linked Lists + Dual Maps.
 * - `keyToNode` map: `key -> Node(key, val, freq)`.
 * - `freqToList` map: `freq -> DoublyLinkedList`.
 * - Track `minFreq`.
 * - On access, increment `node.freq`, move node from `freqToList[oldFreq]` to `freqToList[oldFreq + 1]`.
 * - If `freqToList[oldFreq]` becomes empty and `oldFreq == minFreq`, increment `minFreq`.
 * - On eviction when full, remove node from `freqToList[minFreq].removeTail()`.
 */

import java.util.HashMap;
import java.util.Map;

class LFU_Cache {

    private static class Node {
        int key, val, freq;
        Node prev, next;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.freq = 1;
        }
    }

    private static class DoublyLinkedList {
        Node head, tail;
        int size;

        DoublyLinkedList() {
            head = new Node(-1, -1);
            tail = new Node(-1, -1);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        void addHead(Node node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
            size++;
        }

        void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }

        Node removeTail() {
            if (size == 0) return null;
            Node lfuNode = tail.prev;
            remove(lfuNode);
            return lfuNode;
        }
    }

    private final int capacity;
    private int minFreq;
    private final Map<Integer, Node> keyToNode;
    private final Map<Integer, DoublyLinkedList> freqToList;

    public LFU_Cache(int capacity) {
        this.capacity = capacity;
        this.minFreq = 0;
        this.keyToNode = new HashMap<>();
        this.freqToList = new HashMap<>();
    }

    public int get(int key) {
        if (!keyToNode.containsKey(key)) return -1;
        Node node = keyToNode.get(key);
        updateNode(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (capacity == 0) return;

        if (keyToNode.containsKey(key)) {
            Node node = keyToNode.get(key);
            node.val = value;
            updateNode(node);
        } else {
            if (keyToNode.size() == capacity) {
                DoublyLinkedList minList = freqToList.get(minFreq);
                Node evictNode = minList.removeTail();
                keyToNode.remove(evictNode.key);
            }
            Node newNode = new Node(key, value);
            keyToNode.put(key, newNode);
            minFreq = 1;
            freqToList.computeIfAbsent(1, k -> new DoublyLinkedList()).addHead(newNode);
        }
    }

    private void updateNode(Node node) {
        int oldFreq = node.freq;
        DoublyLinkedList oldList = freqToList.get(oldFreq);
        oldList.remove(node);

        if (oldFreq == minFreq && oldList.size == 0) {
            minFreq++;
        }

        node.freq++;
        freqToList.computeIfAbsent(node.freq, k -> new DoublyLinkedList()).addHead(node);
    }

    public static void main(String[] args) {
        LFU_Cache lfu = new LFU_Cache(2);
        lfu.put(1, 1);
        lfu.put(2, 2);
        System.out.println("Get 1: " + lfu.get(1)); // 1
        lfu.put(3, 3); // evicts key 2
        System.out.println("Get 2: " + lfu.get(2)); // -1
        System.out.println("Get 3: " + lfu.get(3)); // 3
        lfu.put(4, 4); // evicts key 1
        System.out.println("Get 1: " + lfu.get(1)); // -1
        System.out.println("Get 3: " + lfu.get(3)); // 3
        System.out.println("Get 4: " + lfu.get(4)); // 4
    }
}
