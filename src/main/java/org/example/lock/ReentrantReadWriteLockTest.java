package org.example.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁的简单实用
 */
public class ReentrantReadWriteLockTest {

    static ReentrantReadWriteLock rtrwl = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        new Thread(() -> {
            reads();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            writes();
        }, "A").start();
        new Thread(() -> {
            reads();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            writes();
        }, "B").start();

    }

    private static void reads() {
        rtrwl.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+ "-------------reads--------------");
        } finally {
            rtrwl.readLock().unlock();
        }
    }

    private static void writes(){
        rtrwl.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"-------------writes--------------");
        } finally {
            rtrwl.writeLock().unlock();
        }


    }
}
