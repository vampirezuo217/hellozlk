package org.example.mianshi.fbs;

//import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.IZkDataListener;

import java.util.concurrent.CountDownLatch;

/**
 * 此类中有zkLock、zkUnLock方法 通用的方法放在父类ZkAbstractTemplateLock 作为流程骨架
 * 具体细节在子类ZkDistributeLock实现  子类具有所有属性、方法。
 */
public class ZkDistributeLock extends ZkAbstractTemplateLock {
    @Override
    public void waitGetLock() {
        // 监听器
        IZkDataListener iZkDataListener = new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {

            }
            @Override
            public void handleDataDeleted(String s) throws Exception {
                // 删除临时节点后
                if (countDownLatch != null) {
                    countDownLatch.countDown();
                }
            }
        };


//        // 多个jvm 运行
//        zkClient.subscribeDataChanges(path, iZkDataListener);
//        if (zkClient.exists(path)) {
//            // 临时节点只有一个
//            countDownLatch = new CountDownLatch(1);
//            try {
//                // 一直等待
//                countDownLatch.await();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        // 资源释放操作
//        zkClient.unsubscribeDataChanges(path, iZkDataListener);
    }

    /**
     * 临时节点
     */
    @Override
    public boolean tryGetLock() {
        try {
//            zkClient.createEphemeral(path);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }
}
