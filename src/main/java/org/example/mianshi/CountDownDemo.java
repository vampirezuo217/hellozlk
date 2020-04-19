package org.example.mianshi;

import java.util.concurrent.CountDownLatch;

public class CountDownDemo {


    static CountDownLatch countDownLatch = new CountDownLatch(6);

    public static void main(String[] args) {

//        failCase();

        correctDemo();


    }

    public static void correctDemo() {
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 从图书馆离开");
                countDownLatch.countDown();
            }, NameEnum.getName(i).getName()).start();   //这里使用枚举的方式来解决映射值（值很多）
//            }, String.valueOf(i)).start();
        }
        try {
            countDownLatch.await();// 保证在上面的6个线程都执行完之后，主线程在执行的动作
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "\t 管理员闭馆");

    }

    public static void failCase() {

        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 从图书馆离开");
            }, String.valueOf(i)).start();
        }
        System.out.println(Thread.currentThread().getName() + "\t 管理员闭馆");
    }



}
