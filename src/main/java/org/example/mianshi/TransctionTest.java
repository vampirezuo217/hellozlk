package org.example.mianshi;


import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
public class TransctionTest {

    public void tranctionDemo() {
        method1();
    }


//    @Transactional(rollbackFor = Exception.class)
    @Transactional
    public  void method1() {
        try {
            int num = 1/1;
            System.out.println(num);
            // 异常无法传播出方法，导致事务无法回滚
            throw new RuntimeException("pwd runtimeexception");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("create failed!!!");

            // 解决方法
//            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }

    public static void main(String[] args) {
        TransctionTest transctionTest = new TransctionTest();
        transctionTest.tranctionDemo();
    }
}
