package org.example.mianshi;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * v3.0 版本  生产-消费
 * 没有使用synchronized lock
 */
public class ShareDataNew {

    AtomicInteger atomicInteger = new AtomicInteger();  // key
    BlockingQueue<String> blockingQueue;
    private volatile boolean FLAG = true; // 默认开启 生成-消费匹配

    public ShareDataNew(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void product() throws Exception {
        boolean result;
        String key;
        while (FLAG) {
            key = atomicInteger.incrementAndGet()+"";
            result = blockingQueue.offer(key, 2, TimeUnit.SECONDS);
            if (result) {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列" + key + "成功");
            }else {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列" + key + "失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName() + "\t 大老板叫停 FLAG =false 停止生产");
    }

    public void consume() throws Exception {
        String result;
        while (FLAG) {
            //生产-消费匹配1-1
            result = blockingQueue.poll(2, TimeUnit.SECONDS);
            if (result==null || result.equalsIgnoreCase("")) {
                FLAG = false;
                System.out.println(Thread.currentThread().getName() + "\t  2s钟没有取到消息值，停止消费");
                System.out.println();
                System.out.println();
                return;
            }
            System.out.println(Thread.currentThread().getName() + "\t 消费队列" + result + "成功");
        }
    }


    public void stop(){
        this.FLAG =false;
    }
}
