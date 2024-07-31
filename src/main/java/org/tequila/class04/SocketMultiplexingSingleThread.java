package org.tequila.class04;

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

/**
 * @ClassName SocketMultiplexingSingleThread
 * @Description TODO
 * @Author GT-R
 * @Date 2023/7/3110:35
 * @Version 1.0
 */
public class SocketMultiplexingSingleThread {
    private ServerSocketChannel server = null;
    private Selector selector = null;
    private int port = 9090;

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
//                Set<SelectionKey> keys = selector.keys();
//                System.out.println(keys.size());

                while (selector.select(500) > 0) {
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey next = iterator.next();
                        iterator.remove();
                        if (next.isAcceptable()) {
                            acceptHandler(next);
                        } else if (next.isReadable()) {
                            readHandler(next);
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void readHandler(SelectionKey next) {
        SocketChannel socketChannel = (SocketChannel) next.channel();
        ByteBuffer attachment = (ByteBuffer) next.attachment();
        attachment.clear();
        int read = 0;
        try {
            while (true) {
                read = socketChannel.read(attachment);
                if (read > 0) {
                    attachment.flip();
                    while (attachment.hasRemaining()) {
                        socketChannel.write(attachment);
                    }
                    attachment.clear();
                } else if (read == 0) {
                    break;
                } else {
                    socketChannel.close();
                    break;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void acceptHandler(SelectionKey next) {

        try {
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) next.channel();
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false);
            ByteBuffer byteBuffer = ByteBuffer.allocate(8192);
            socketChannel.register(selector, SelectionKey.OP_READ, byteBuffer);
            System.out.println("-------------------------------------------");
            System.out.println("新客户端：" + socketChannel.getRemoteAddress());
            System.out.println("-------------------------------------------");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testSelector() {
        SocketMultiplexingSingleThread singleThread = new SocketMultiplexingSingleThread();
        singleThread.start();
    }
}
