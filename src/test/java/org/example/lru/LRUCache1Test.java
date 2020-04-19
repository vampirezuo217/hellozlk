package org.example.lru;


import org.junit.Assert;
import org.junit.Test;

public class LRUCache1Test {

    @Test
    public void testGet() {
        LRUCache1 lruCache = new LRUCache1(3);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        lruCache.put(3, 3);
        lruCache.put(4, 4);
        Assert.assertEquals(-1, lruCache.get(1));
    }

}