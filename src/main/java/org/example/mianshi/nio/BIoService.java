package org.example.mianshi.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class BIoService {

    public static void main(String[] args) throws IOException {

//        singleThread();

        nThread();

    }

    public static void nThread() throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(9876));
        while (true){
            System.out.println("等待");
            Socket accept = serverSocket.accept();//阻塞  释放cpu
            System.out.println("连接成功");
            Thread thread = new Thread(new threadSocket(accept));
            thread.start();
        }
    }


    static class threadSocket implements Runnable{
        byte[] bytes = new byte[1024];
        Socket socket;
        public threadSocket(Socket socket) {
            this.socket = socket;
        }
        /**
         * 处理每个客户端连接
         */
        @Override
        public void run() {
            try {
                socket.getInputStream().read(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String content = new String(bytes);
            System.out.println(content);
        }
    }

    public static void singleThread() throws IOException {
        byte[] bytes = new byte[1024];
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(9876));

        while (true) {
            System.out.println("等待");
            Socket accept = serverSocket.accept();//阻塞  释放cpu
            System.out.println("连接成功");
            int read = accept.getInputStream().read(bytes);
            System.out.println(read);
            String content = new String(bytes);
            System.out.println(content);
        }
    }
}
