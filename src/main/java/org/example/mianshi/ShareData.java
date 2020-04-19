package org.example.mianshi;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ShareData {

    private int symbo = 1;// 1 A 2 B 3C  标志位

    private Lock lock = new ReentrantLock(); // 锁

    Condition condition1 = lock.newCondition();   // 钥匙
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();


    public void print5() {
        lock.lock();
        try {
            while (symbo != 1) {//不是A
                condition1.await();  //A等待
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t " + i);
            }
            symbo = 2;
            condition2.signal();// 唤醒唤醒线程

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public void print10() {
        lock.lock();
        try {
            while (symbo != 2) {
                condition2.await();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t " + i);
            }
            symbo = 3;
            condition3.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15() {
        lock.lock();
        try {
            while (symbo != 3) {
                condition3.await();
            }
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t " + i);
            }
            symbo = 1;
            condition1.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
