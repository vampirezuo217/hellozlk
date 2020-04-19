package org.example.mianshi;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 */
public class ReentReadWriterLockDemo {


    private volatile Map<String,Object> map  = new HashMap<>();
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();


    private void put(String key, Object value){
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在写入 " + key);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t 正在写入 " + key);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            readWriteLock.writeLock().unlock();
        }
    }

    private void get(String key){
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在读入 " + key);
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 正在读入 " + key);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }


    public static void main(String[] args) {

        ReentReadWriterLockDemo demo = new ReentReadWriterLockDemo();

        for (int i = 0; i < 5; i++) {
            int temp = i;
            new Thread(() -> {
                demo.put(temp + "", temp + "");
            }, String.valueOf(i)).start();
        }

        for (int i = 0; i < 5; i++) {
            int temp = i;
            new Thread(() -> {
                demo.get(temp + "");
            }, String.valueOf(i)).start();
        }

    }
}
