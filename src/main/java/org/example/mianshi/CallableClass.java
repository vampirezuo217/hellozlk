package org.example.mianshi;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class CallableClass  implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        TimeUnit.SECONDS.sleep(1);
        System.out.println(Thread.currentThread().getName()+ "########Callable方式创建线程");
        return 1;
    }
}
