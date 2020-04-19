package org.example.mianshi;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Thread3 {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(Runtime.getRuntime().availableProcessors());
//        createThread2();
//        createThread3();
//        problem();
//        solve();

        //还有一种是线程池方式获取线程
    }

    public static void solve() {
        FutureTask<Integer> futureTask = new FutureTask<>(new CallableClass());
        FutureTask<Integer> futureTask1 = new FutureTask<>(new CallableClass());
        new Thread(futureTask, "AA").start();
        new Thread(futureTask1, "BB").start();
    }

    public static void problem() {
        FutureTask<Integer> futureTask = new FutureTask<>(new CallableClass());
        // 多个线程执行一个futureTask(两个线程干了同一件事情),计算结果算一次
        new Thread(futureTask, "AA").start();
        new Thread(futureTask, "BB").start();
    }

    public static void createThread3() throws InterruptedException, ExecutionException {
        int r1 = 100;
        //第3中创建线程 （并发、异步 场景） 尽量避免堵塞  Runnale - RunableFure - FutureTask
        FutureTask<Integer> futureTask = new FutureTask<>(new CallableClass());
        new Thread(futureTask, "BB").start();

        System.out.println("-----main的计算---------");

        while (!futureTask.isDone()) {
            System.out.println("在等futureTask创建的线程");
        }
        // futureTask最好是放在最后
        System.out.println("最后的计算结果result :" + r1 + futureTask.get());
    }

    public static void createThread2() {
        //第二种创建线程
        Thread thread = new Thread(new RunnalbeClass(), "AA");
        thread.start();
        System.out.println(thread.getName());
    }
}
