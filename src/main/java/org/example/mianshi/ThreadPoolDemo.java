package org.example.mianshi;


import java.util.concurrent.*;

public class ThreadPoolDemo {

    public static void main(String[] args) {
        // Executors中创建的线程池都使用了ThreadPoolExecutor类
//        fixThreadPool();
//        singleThreadPool();
//        cacheThreadPool();

        customThreadPool();
    }


    public static void fixThreadPool() {
        ExecutorService threadPool = Executors.newFixedThreadPool(5); //1池5线程
        handle(threadPool);
    }

    public static void singleThreadPool() {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();//1池1线程
        handle(threadPool);

    }

    public static void cacheThreadPool() {
        ExecutorService threadPool = Executors.newCachedThreadPool();//1池N线程
        handle(threadPool);
    }

    public static void handle(ExecutorService threadPool) {
        // 10个用户来办理业务，每个用户对应一个请求
        try {
            for (int i = 0; i < 10; i++) {
                threadPool.execute(() -> System.out.println(Thread.currentThread().getName() + "\t 处理业务员"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }


    public static void customThreadPool(){
        ExecutorService customThreadPool = new ThreadPoolExecutor(1,
                2,1L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1),
                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.AbortPolicy());
                new ThreadPoolExecutor.CallerRunsPolicy());
//                new ThreadPoolExecutor.DiscardOldestPolicy());
//                new ThreadPoolExecutor.DiscardPolicy());

        handle(customThreadPool);
    }


}
