package org.example.mianshi;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class OOM {

    static class test{

    }

    public static void main(String[] args) {

//        stackOverFlow();//栈异常问题
//        HeapOutOfMemory();//堆内存不够问题
//        overHeadLimitExeed(); //FullGC高问题
//        newNativeThread(); // 创建本地线程问题
//        DirectBufferMemory(); // 本地内存问题
        metaSpace(args); //元空间溢出问题

    }

    /**
     * Caused by: java.lang.OutOfMemoryError: Metaspace
     * -XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=9m -XX:+PrintGCDetails
     */
    public static void metaSpace(String[] args) {
        int i =0;
        try {
           while (true){
               ++i;
               Enhancer enhancer = new Enhancer();
               enhancer.setSuperclass(test.class);
               enhancer.setUseCache(false);
               enhancer.setCallback(new MethodInterceptor() {
                   @Override
                   public Object intercept(Object o, Method method, Object[] objects,
                                           MethodProxy methodProxy) throws Throwable {
                       return methodProxy.invokeSuper(o, args);
                   }
               });
               enhancer.create();
           }
        } catch (Throwable e) {
            System.out.println("第" + i + "次 static class 创建 发生异常");
            e.printStackTrace();

        }


    }

    /**
     * Exception in thread "main" java.lang.OutOfMemoryError: unable to create new native thread
     * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
     */
    public static void newNativeThread() {
        for (int i = 0;   ; i++) {
            System.out.println("----------------- i = " + i);
            int temp = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t" + temp);
                try {
                    //线程创建完 不退出
                    TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    /**
     * Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory
     * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
     */
    public static void DirectBufferMemory() {
        System.out.println("配置的maxDirectMemory:" + (sun.misc.VM.maxDirectMemory() / (double) 1024 / 1024) + "MB");

        ByteBuffer byteBuffers = ByteBuffer.allocateDirect(6 * 1024 * 1024);

    }

    /**
     * 大分部时间做GC 回收结果很小 白白浪费cpu 飙升，内存很高  形成恶性循环
     * java.lang.OutOfMemoryError: GC overhead limit exceeded
     * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
     */
    public static void overHeadLimitExeed() {
        int i = 0;
        List<String> list = new ArrayList<>();
        try {
            while (true) {
                list.add(String.valueOf(++i).intern());
            }
        } catch (Throwable e) {
            System.out.println("************************* i 值" + i);
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * -Xmx1m
     * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
     */
    public static void HeapOutOfMemory() {
        String s = "a";
        while (true) {
            s = s + new Random().nextInt(111);
        }
    }

    /**
     * Exception in thread "main" java.lang.StackOverflowError
     */
    public static void stackOverFlow() {
        stackOverFlow();
    }
}
