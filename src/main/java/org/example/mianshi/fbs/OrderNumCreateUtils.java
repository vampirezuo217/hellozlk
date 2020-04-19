package org.example.mianshi.fbs;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OrderNumCreateUtils {

    private static int number = 0;

    private Lock lock = new ReentrantLock();

    /**
     * 单机版本（1jvm） 多线程处理
     */
    public String getOrderNum() {
        String result = null;
        lock.lock();
        try {
            result = "\t 订单号为：" + (++number);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return result;
    }
}
