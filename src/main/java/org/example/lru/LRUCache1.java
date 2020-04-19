package org.example.lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *  时间复杂度：对于 put 和 get 操作复杂度是 O(1)O(1)，因为有序字典中的所有操作：
 *  get/in/set/move_to_end/popitem（get/containsKey/put/remove）都可以在常数时间内完成。
 *  * 空间复杂度：O(capacity)O(capacity)，因为空间只用于有序字典存储最多 capacity + 1 个元素。
 */
public class LRUCache1 extends LinkedHashMap<Integer, Integer> {

    private int capacity;

    public LRUCache1(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }
}
