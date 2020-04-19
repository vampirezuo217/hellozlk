package org.example.mianshi;

public class HelloGc {

    public static void main(String[] args) {

        long initMem = Runtime.getRuntime().totalMemory();
        long maxMem = Runtime.getRuntime().maxMemory();
        // 内存1/64
        System.out.println("jvm init heap mem is "+ initMem/(double)1024/1024+"MB");
        // 内存的1/4
        System.out.println("jvm max heap mem is "+ maxMem/(double)1024/1024+"MB");


        byte[] bytes = new byte[7*1024*1024];

    }
}
