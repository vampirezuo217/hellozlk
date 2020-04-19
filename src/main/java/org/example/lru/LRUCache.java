package org.example.lru;

import java.util.Hashtable;

public class LRUCache {

    private int size;
    private int capacity;
    private DLinkedNode head, tail;

    // 实现map接口 继承字典dictory 本质是一个map  当size>capacitys时  map中size个数是真实存在的
    private Hashtable<Integer, DLinkedNode> cache = new Hashtable<Integer, DLinkedNode>();

    // 初始化cache
    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        head = new DLinkedNode();
        // head.prev = null;
        tail = new DLinkedNode();
        // tail.next = null;
        head.next = tail;
        tail.prev = head;
    }

    /**
     * 头插法 right after head.
     */
    private void addNode(DLinkedNode node) {
        node.prev = head;
        node.next = head.next;

        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(DLinkedNode node) {

        DLinkedNode prev = node.prev;
        DLinkedNode next = node.next;

        prev.next = next;
        next.prev = prev;
    }

    /**
     * 该节点当做head指向的节点
     */
    private void moveToHead(DLinkedNode node) {
        removeNode(node);
        addNode(node);
    }

    private DLinkedNode popTailReference() {
        // res指向tail前驱节点
        DLinkedNode res = tail.prev;
        // 从链表中删除掉前驱节点
        removeNode(res);
        // 保留删除前驱结点引用
        return res;
    }

    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null) return -1;

        // move the accessed node to the head;
        moveToHead(node);

        return node.value;
    }

    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);

        if (node == null) {
            DLinkedNode newNode = new DLinkedNode();
            newNode.key = key;
            newNode.value = value;

            cache.put(key, newNode);
            addNode(newNode);
            ++size;

            if (size > capacity) {
                // 获取tail前驱节点引用
                DLinkedNode res = popTailReference();
                // 从哈希表中删除
                cache.remove(res.key);
                --size;
            }
        } else {
            // update the value.
            node.value = value;
            moveToHead(node);
        }
    }

    class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;
    }
}
