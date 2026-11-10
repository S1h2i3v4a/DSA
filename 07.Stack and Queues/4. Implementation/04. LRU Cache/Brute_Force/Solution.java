/*
QUESTION:
Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
Implement `LRUCache` with `get(key)` and `put(key, value)`.

APPROACH:
- Store keys in an `ArrayList<Integer>` representing access order (most recent at end).
- Store key-value mappings in a `HashMap<Integer, Integer>`.
- On `get(key)`:
  - If key exists, remove from its current position in the list and append to end. Return value.
  - Else return -1.
- On `put(key, value)`:
  - If key exists, update value and move key to end.
  - If capacity exceeded, remove index 0 (least recently used) from both list and map.
  - Add new key at end of list and into map.

TIME COMPLEXITY: O(capacity) per get/put due to linear list search and removal.
SPACE COMPLEXITY: O(capacity) to store keys and values.
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Solution {
    private int capacity;
    private Map<Integer, Integer> map;
    private ArrayList<Integer> order;

    public Solution(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.order = new ArrayList<>();
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        order.remove((Integer) key);
        order.add(key);
        return map.get(key);
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            order.remove((Integer) key);
        } else if (order.size() == capacity) {
            int lru = order.remove(0);
            map.remove(lru);
        }
        order.add(key);
        map.put(key, value);
    }
}
