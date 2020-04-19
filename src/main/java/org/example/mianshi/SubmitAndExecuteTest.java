package org.example.mianshi;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class SubmitAndExecuteTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        final ExecutorService pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());

        List<Integer> list = Lists.newArrayList(1, 2, 3, null);

//        //1.使用submit
//        pool.submit(() -> {
//            list.parallelStream().map(a -> a.toString()).collect(Collectors.toList());
//        });
//        TimeUnit.SECONDS.sleep(3);
//        //2.使用 execute
//        pool.execute(() -> {
//            list.parallelStream().map(a -> a.toString()).collect(Collectors.toList());
//        });
        //3.使用submit，调用get()
        pool.submit(() -> {
            list.parallelStream().map(a -> a.toString()).collect(Collectors.toList());
        }).get();
    }
}
