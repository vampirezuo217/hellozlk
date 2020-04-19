package org.example.mianshi.fbs;

import org.I0Itec.zkclient.ZkClient;

import java.util.concurrent.CountDownLatch;

/**
 * 模板方法抽象类
 */
public abstract class ZkAbstractTemplateLock implements ZkLock {

    protected String path = "/zklock01";
    protected CountDownLatch countDownLatch = null;
//    ZkClient zkClient = new ZkClient("192.168.1.106:2181", 45 * 1000);

    /**
     * 加锁  zkclient创建临时节点
     */
    @Override
    public void zkLock() {
        if (tryGetLock()) {
            System.out.println(Thread.currentThread().getName() + "\t 获取锁成功");
        } else {
            waitGetLock();
            //递归 jvm获取加锁
            zkLock();
        }

    }

    /**
     * 解锁   zkclinet 退出
     */
    @Override
    public void zkUnLock() {
//        if (zkClient != null) {
//            zkClient.close();
//        }
//        System.out.println(Thread.currentThread().getName() + "\t 释放锁成功");
//        System.out.println();
//        System.out.println();
    }


    public abstract void waitGetLock();

    public abstract boolean tryGetLock();
}
