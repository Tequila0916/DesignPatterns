package org.tequila.class05;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName SocketMultiplexingSingleThread
 * @Description TODO
 * @Author GT-R
 * @Date 2023/7/3115:41
 * @Version 1.0
 */
public class SocketMultiplexingSingleThread {
    private ServerSocketChannel server = null;
    private int port = 9090;
    private Selector selector = null;

    public void initServer() {
        try {
            server = ServerSocketChannel.open();
            server.configureBlocking(false);
            server.bind(new InetSocketAddress(port));

            selector = Selector.open();
            server.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void start() {
        initServer();
        System.out.println("服务器启动了。。。。。");
        try {
            while (true) {
                while (selector.select(500) > 0) {
                    Set<SelectionKey> keySet = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = keySet.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey selectionKey = iterator.next();
                        iterator.remove();
                        if (selectionKey.isAcceptable()) {
                            acceptHandler(selectionKey);
                        } else if (selectionKey.isReadable()) {
                            readHandler(selectionKey);
                        } else if (selectionKey.isWritable()) {
                            writeHandler(selectionKey);
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeHandler(SelectionKey key) {
        System.out.println("write handler...");
        SocketChannel client = (SocketChannel) key.channel();
        ByteBuffer byteBuffer = (ByteBuffer) key.attachment();
        byteBuffer.flip();
        while (byteBuffer.hasRemaining()) {
            try {
                client.write(byteBuffer);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            TimeUnit.SECONDS.sleep(2);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        byteBuffer.clear();
        key.cancel();
        try {
            client.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void readHandler(SelectionKey key) {
        System.out.println("read handler.....");
        SocketChannel client = (SocketChannel) key.channel();
        ByteBuffer byteBuffer = (ByteBuffer) key.attachment();
        byteBuffer.clear();
        int read = 0;
        try {
            while (true) {
                read = client.read(byteBuffer);
                if (read > 0) {
                    client.register(selector, SelectionKey.OP_WRITE, byteBuffer);
                } else if (read == 0) {
                    break;
                } else {
                    client.close();
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void acceptHandler(SelectionKey key) {
        try {
            ServerSocketChannel server = (ServerSocketChannel) key.channel();
            SocketChannel client = server.accept();
            client.configureBlocking(false);
            ByteBuffer byteBuffer = ByteBuffer.allocate(8192);
            client.register(selector, SelectionKey.OP_READ, byteBuffer);
            System.out.println("-------------------------------------------");
            System.out.println("新客户端：" + client.getRemoteAddress());
            System.out.println("-------------------------------------------");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testWrite() {
        SocketMultiplexingSingleThread thread = new SocketMultiplexingSingleThread();
        thread.start();
    }
}
