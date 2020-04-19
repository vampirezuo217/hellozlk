package org.example.mianshi;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABA {

    private static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    /**
     * 初始值100，时间戳1
     */
    private static AtomicStampedReference<Integer> timestampAtomicReference = new AtomicStampedReference<>(100, 1);

    public static void main(String[] args) {

//        productABA();
        solveABA();

    }


    public static void solveABA() {
        /**
         * 模拟ABA操作
         */
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t 第一次版本号" + timestampAtomicReference.getStamp());
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(timestampAtomicReference.compareAndSet(100, 101,
                    timestampAtomicReference.getStamp(), timestampAtomicReference.getStamp() + 1));
            System.out.println(Thread.currentThread().getName() + "\t 第二次版本号" + timestampAtomicReference.getStamp());

            System.out.println(timestampAtomicReference.compareAndSet(101, 100,
                    timestampAtomicReference.getStamp(), timestampAtomicReference.getStamp() + 1));
            System.out.println(Thread.currentThread().getName() + "\t 第三次版本号" + timestampAtomicReference.getStamp());
        }, "t3").start();

        new Thread(() -> {
            int version = timestampAtomicReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t 第一次版本号" + version);
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean result = timestampAtomicReference.compareAndSet(100, 2020, version, version + 1);
            System.out.println(Thread.currentThread().getName() + "\t 修改成功？" + result);
        }, "t4").start();
    }

    public static void productABA() {
        new Thread(() -> {
            atomicReference.compareAndSet(100, 101);
            atomicReference.compareAndSet(101, 100);
        }, "t1").start();


        new Thread(() -> {
            // 保证t1先完成
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(atomicReference.compareAndSet(100, 2020) + "\t" + atomicReference.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2").start();
    }
}
