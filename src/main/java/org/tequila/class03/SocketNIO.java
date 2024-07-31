package org.tequila.class03;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName SocketNIO
 * @Description TODO
 * @Author GT-R
 * @Date 2023/7/2909:21
 * @Version 1.0
 */
public class SocketNIO {
    public static void main(String[] args) throws IOException, InterruptedException {
        LinkedList<SocketChannel> clients = new LinkedList<>();
        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        socketChannel.bind(new InetSocketAddress(9090));
        socketChannel.configureBlocking(false);
        while (true) {
            TimeUnit.SECONDS.sleep(1);
            SocketChannel client = socketChannel.accept();
//            if (client == null) {
//                System.out.println("null..");
//            } else {
            if (client != null) {
                client.configureBlocking(false);
                int port = client.socket().getPort();
                System.out.println("client..port = " + port);
                clients.add(client);
            }
            ByteBuffer byteBuffer = ByteBuffer.allocate(4096);
            for (SocketChannel c : clients) {
                int read = c.read(byteBuffer);
                if (read > 0) {
                    byteBuffer.flip();
                    byte[] bytes = new byte[byteBuffer.limit()];
                    byteBuffer.get(bytes);
                    String result = new String(bytes);
                    System.out.println(c.socket().getPort() + " : " + result);
                    byteBuffer.clear();
                }
            }
        }

    }
}
