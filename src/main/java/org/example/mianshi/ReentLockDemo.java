package org.example.mianshi;


/**
 * 可重入锁
 */
public class ReentLockDemo {

    public static void main(String[] args) {
        Phone phone = new Phone();

        new Thread(()-> phone.sendSMS(),"t1").start();
        new Thread(()-> phone.sendSMS(),"t2").start();
    }
}


class Phone{
    /**
     * 同一线程在外层方法获得锁后，内层递归函数获取该锁的代码
     */
    public synchronized void  sendSMS(){
        System.out.println(Thread.currentThread().getId()+ "\t 发短信");
//        线程可以进入任何一个已经拥有的锁所同步着的代码块
        sendEmail();
    }

    public synchronized void  sendEmail(){
        System.out.println(Thread.currentThread().getId()+ "\t ###发邮件");
    }

}
