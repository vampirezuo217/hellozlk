package org.example.mianshi;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁实现
 * 获取锁的线程不会立即阻塞，而是采用循环的方式去尝试获取锁。
 */
public class SpinLockDemo {

    /**
     * 原子引用线程
     */
    private AtomicReference<Thread> atomicReference = new AtomicReference<>();


    public static void main(String[] args) {

        SpinLockDemo spinLockDemo = new SpinLockDemo();

        new Thread(()->{
            spinLockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(2);
            }catch (Exception e){
                e.printStackTrace();
            }
            spinLockDemo.myUnLock();
        },"AA").start();



        new Thread(()->{
            spinLockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(1);
            }catch (Exception e){
                e.printStackTrace();
            }
            spinLockDemo.myUnLock();
        },"BB").start();

    }

    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t  come in myLock! ");
        while (!atomicReference.compareAndSet(null, thread)) {
//            System.out.println(thread.getName() + "\t 主内存数据不为null");
        }
//        System.out.println(thread.getName()+ "\t 111111111111111");
    }

    public void myUnLock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + "\t invoked myUnLock");

    }
}
