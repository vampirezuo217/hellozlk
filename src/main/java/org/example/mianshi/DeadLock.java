package org.example.mianshi;

/**
 * 1.模拟死锁的情况
 * 2.怎么排查是死锁  jps  jstat  jstack
 * 3.解决死锁问题
 */
public class DeadLock {

    public static void main(String[] args) {
        HoldLockThread holdLockThread = new HoldLockThread("a","b");
        new Thread(holdLockThread,"ThreadAAAA").start();

        HoldLockThread holdLockThread1 = new HoldLockThread("b","a");
        new Thread(holdLockThread1,"ThreadBBBB").start();

    }
}
