package org.example.mianshi.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class BIOClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("127.0.0.1",9876));
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("请输入：");
            String next = scanner.next();
            socket.getOutputStream().write(next.getBytes());
        }
//        socket.close();
    }
}
