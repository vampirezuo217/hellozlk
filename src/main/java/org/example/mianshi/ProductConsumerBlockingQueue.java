package org.example.mianshi;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 接口 抽象  写代码兼容、适配  查问题要落地要细节
 */
public class ProductConsumerBlockingQueue {


    public static void main(String[] args) {

        ShareDataNew shareDataNew = new ShareDataNew(new ArrayBlockingQueue<>(10));

        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"\t 生产线程启动");
                shareDataNew.product();
                System.out.println();
                System.out.println();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"prod").start();

        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"\t 消费线程启动");
                shareDataNew.consume();
                System.out.println();
                System.out.println();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"consumer").start();


        try {
            TimeUnit.SECONDS.sleep(5);
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println(Thread.currentThread().getName()+"大老版main线程叫停，活动结束");
            shareDataNew.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
