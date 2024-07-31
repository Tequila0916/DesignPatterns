package org.tequila.class02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName TestSocket
 * @Description TODO
 * @Author GT-R
 * @Date 2023/7/2514:28
 * @Version 1.0
 */
public class TestSocket {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8090);
        System.out.println("step1: new ServerSocket(8090)");
        while (true) {
            Socket clinet = serverSocket.accept(); // 阻塞
            System.out.println("step2: client " + clinet.getPort());
            new Thread(new Runnable() {
                Socket socket;

                public Runnable setSocket(Socket socket) {
                    this.socket = socket;
                    return this;
                }

                @Override
                public void run() {

                    BufferedReader reader = null;
                    try {
                        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        while (true) {
                            System.out.println(reader.readLine());  //阻塞
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }.setSocket(clinet)).start();
        }
    }
}
