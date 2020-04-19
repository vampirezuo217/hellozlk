package org.example.mianshi.fbs;

/**
 * 测试订单
 *
 *
 */
public class OrderClient {

    public static void main(String[] args) {
        //单机版本
        orderNumber();
        //多机版本(面向接口编程)
//        getDistributionOrderNumber();

    }

    public static void getDistributionOrderNumber() {
        for (int i = 0; i < 10; i++) {
            new Thread(()-> new OrderService().getOrderNumber(),
                    String.valueOf(i)).start();
        }
    }

    public static void orderNumber() {
        OrderService orderService = new OrderService();
        for (int i = 0; i < 30; i++) {
            new Thread(()-> System.out.println(orderService.getOrderNumber()),
                    String.valueOf(i)).start();
        }
    }
}
