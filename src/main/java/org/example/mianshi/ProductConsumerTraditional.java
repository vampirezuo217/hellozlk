package org.example.mianshi;


/**
 * 此例针对一个变量，两个线程交替执行加一、减一操作
 * 传统生产、消费模式
 * 线程加锁、操作、资源
 * 判断、干活、通知
 * 多线程中防止虚假唤醒 while使用
 */
public class ProductConsumerTraditional {

    public static void main(String[] args) {

        KongTiao kongTiao = new KongTiao();

        new Thread(()->{
            for (int i = 0; i < 3; i++) {
                try {
                    kongTiao.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();

        new Thread(()->{
            for (int i = 0; i < 3; i++) {
                try {
                    kongTiao.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();
    }

}
