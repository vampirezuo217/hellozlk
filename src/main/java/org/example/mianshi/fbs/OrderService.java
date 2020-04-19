package org.example.mianshi.fbs;


/**
 * 业务订单数
 */
public class OrderService {

    private OrderNumCreateUtils orderNumCreateUtils = new OrderNumCreateUtils();

    /**
     * 自定义实现分布式锁的机制原理接口、和实现
     */
//    private ZkLock zkLock = new ZkDistributeLock();

    /**
     * 单机 获取订单数
     */
    public String getOrderNumber(){
        return orderNumCreateUtils.getOrderNum();
    }

    /**
     * 分布式锁 获取订单数
     */
//    public void getDistributionOrderNumber(){
//        zkLock.zkLock();
//        try {
//            System.out.println("---分布式锁订单数---"+orderNumCreateUtils.getOrderNum());
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            zkLock.zkUnLock();
//        }
//    }



}
