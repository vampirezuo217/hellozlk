package org.example.mianshi;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 资源类空调
 */
public class KongTiao {

    // 温度
    private int num = 0;

    //操作用的加锁
    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    //操作+1方法
    public void increment() throws InterruptedException {
        increOperate();
    }

    //操作-1方法
    public void decrement() throws InterruptedException {
        decOperate();
    }

    /**
     * 加锁操作
     */
    public void increOperate() throws InterruptedException {
        lock.lock();
        try {
            product();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 加锁操作
     */
    public void decOperate() throws InterruptedException {
        lock.lock();
        try {
            consumer();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 生产
     */
    public void product() throws InterruptedException {
        // 判断
        while (num != 0) {
            // 等待不能生产
            condition.await();
        }
        num++;
        System.out.println(Thread.currentThread().getName() + "\t" + num);
        // 通知唤醒
        condition.signalAll();
    }

    /**
     * 消费
     */
    public void consumer() throws InterruptedException {
        // 判断
        while (num == 0) {
            // 等待不能消费
            condition.await();
        }
        num--;
        System.out.println(Thread.currentThread().getName() + "\t" + num);
        // 通知唤醒
        condition.signalAll();
    }

}
