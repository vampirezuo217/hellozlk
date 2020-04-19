package org.example.mianshi.completablefuture;

import java.util.concurrent.*;

public class demo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(() -> {
            TimeUnit.SECONDS.sleep(5);
            return "I am finished.";
        });

        CompletableFuture completableFuture = new CompletableFuture();
    }
}
