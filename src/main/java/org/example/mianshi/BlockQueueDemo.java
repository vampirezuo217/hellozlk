package org.example.mianshi;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class BlockQueueDemo {

    public static void main(String[] args) {

        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        BlockingQueue<String> stringSynchronousQueue = new SynchronousQueue<>();

        Exception(blockingQueue);
        Value(blockingQueue);
        block(blockingQueue);
        block4time(blockingQueue);
        synchronousqueue(stringSynchronousQueue);

    }

    /**
     * 同步队列、不存储元素
     */
    public static void synchronousqueue(BlockingQueue<String> blockingQueue1) {
        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+ "\t put 1");
                blockingQueue1.put("1");
                System.out.println(Thread.currentThread().getName()+ "\t put 2");
                blockingQueue1.put("2");
                System.out.println(Thread.currentThread().getName()+ "\t put 3");
                blockingQueue1.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"AAA").start();


        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName()+ "\t take 1");
                blockingQueue1.take();
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName()+ "\t take 2");
                blockingQueue1.take();
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName()+ "\t take 3");
                blockingQueue1.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"AAA").start();
    }

    /**
     * 超时时间内阻塞
     */
    public static void block4time(BlockingQueue<String> blockingQueue) {
        try {
            blockingQueue.offer("1",2, TimeUnit.SECONDS);
            blockingQueue.offer("2",2, TimeUnit.SECONDS);
            blockingQueue.offer("3",2, TimeUnit.SECONDS);
            blockingQueue.offer("3",2, TimeUnit.SECONDS);

            blockingQueue.poll(2,TimeUnit.SECONDS);
            blockingQueue.poll(2,TimeUnit.SECONDS);
            blockingQueue.poll(2,TimeUnit.SECONDS);
            blockingQueue.poll(2,TimeUnit.SECONDS);
            blockingQueue.poll(2,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 一直阻塞
     */
    public static void block(BlockingQueue<String> blockingQueue) {
        try {
            blockingQueue.put("1");
            blockingQueue.put("2");
            blockingQueue.put("3");
            System.out.println("-------------");;
//            blockingQueue.put("x");

            blockingQueue.take();
            blockingQueue.take();
            blockingQueue.take();
//            blockingQueue.take();
            System.out.println("+++++++++++++");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成、消费时   返回true /false
     */
    public static void Value(BlockingQueue<String> blockingQueue) {
        System.out.println(blockingQueue.offer("1"));
        System.out.println(blockingQueue.offer("2"));
        System.out.println(blockingQueue.offer("3"));
        System.out.println(blockingQueue.offer("x"));

        System.out.println(blockingQueue.peek());

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
    }

    /**
     * 生成、消费  会产生异常
     */
    public static void Exception(BlockingQueue<String> blockingQueue) {
        // 抛出异常
        System.out.println(blockingQueue.add("1"));
        System.out.println(blockingQueue.add("2"));
        System.out.println(blockingQueue.add("3"));
        System.out.println(blockingQueue.add("x"));

        System.out.println(blockingQueue.element());

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
    }
}
